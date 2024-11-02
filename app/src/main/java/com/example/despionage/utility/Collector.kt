package com.example.despionage.utility

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import java.util.Locale

interface HardwareCollector {
    fun getDeviceBrand(): String
    fun getDeviceModel(): String
    fun getDeviceSOC(): String
    fun getScreenHeight(): Int
    fun getScreenWidth(): Int
}

interface SystemCollector {

}

interface SettingCollector {
    fun getDeviceName(): String
    fun getDeviceUser(): String
    fun getDevStatus(): Boolean
}

interface UsageCollector {
    fun getInstalledApps(): List<ApplicationInfo>
}

class Collector (
    val Hardware: HardwareCollector,
    val System: SystemCollector,
    val Settings: SettingCollector,
    val Usage: UsageCollector
) {
    companion object {
        fun getInstance(context: Context): Collector {
            return Collector(
                DfHardwareCollector(context),
                DfSystemCollector(),
                DfSettingCollector(context),
                DfUsageCollector(context)
            )
        }
    }
}

class DfHardwareCollector(private val context: Context) : HardwareCollector {

    private var wm: WindowManager? = null

    override fun getDeviceBrand(): String {
        @Suppress("DEPRECATION")
        return Build.BRAND.capitalize(Locale.ROOT)
    }

    override fun getDeviceModel(): String {
        return Build.MODEL
    }

    override fun getDeviceSOC(): String {
        @Suppress("DEPRECATION")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            "${Build.SOC_MANUFACTURER} ${Build.SOC_MODEL}".capitalize(Locale.ROOT)
        } else {
            "Unknown"
        }
    }

    override fun getScreenHeight(): Int {
        if (wm == null) wm = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            wm!!.currentWindowMetrics.bounds.height()
        } else {
            wm!!.defaultDisplay.height
        }
    }

    override fun getScreenWidth(): Int {
        if (wm == null) wm = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            wm!!.currentWindowMetrics.bounds.width()
        } else {
            wm!!.defaultDisplay.width
        }
    }
}

class DfSystemCollector: SystemCollector {

}

class DfSettingCollector(private val context: Context) : SettingCollector {

    override fun getDeviceName(): String {
        return Settings.Global.getString(context.contentResolver, "device_name") ?: "Unknown Device"
    }

    override fun getDeviceUser(): String {
        return Build.USER
    }

    override fun getDevStatus(): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            0
        ) != 0
    }
}

class DfUsageCollector(private val context: Context) : UsageCollector {

    @SuppressLint("QueryPermissionsNeeded")
    override fun getInstalledApps(): List<ApplicationInfo> {
        return context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }
}