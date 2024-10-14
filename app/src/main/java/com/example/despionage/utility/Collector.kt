package com.example.despionage.utility

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import java.util.Locale

interface Collector {
    fun getDeviceName(): String
    fun getDeviceBrand(): String
    fun getDeviceModel(): String
    fun getDeviceSOC(): String
    fun getDeviceUser(): String
    fun getDevStatus(): Boolean
    fun getInstalledApps(): List<ApplicationInfo>

    companion object {
        fun getInstance(context: Context): Collector {
            return DefaultCollector(context)
        }
    }
}

class DefaultCollector(val context: Context) : Collector {
    override fun getDeviceName(): String {
        return Settings.Global.getString(context.contentResolver, "device_name") ?: "Unknown Device"
    }

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

    @SuppressLint("QueryPermissionsNeeded")
    override fun getInstalledApps(): List<ApplicationInfo> {
        return context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }
}