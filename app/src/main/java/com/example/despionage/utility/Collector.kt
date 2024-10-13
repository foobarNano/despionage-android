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

    @SuppressLint("QueryPermissionsNeeded")
    override fun getInstalledApps(): List<ApplicationInfo> {
        return context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }
}