package com.joye.jiang.common.sdk

import android.app.Application
import android.content.pm.PackageManager.NameNotFoundException
import com.qmuiteam.qmui.util.QMUIPackageHelper

/**
 * 初始化application参数,很多工具类需要application参数,能直接在application初始化就尽量在application里面初始化，
 */
class ApplicationUtils private constructor() {
    private lateinit var application: Application

    companion object {
        @JvmStatic
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = ApplicationUtils()
    }

    fun init(application: Application) {
        this.application = application
    }

    fun getApplication(): Application {
        return application
    }

    /**
     * 获取应用程序的外部版本号
     *
     * @return 外部版本号
     */
    fun getVersionName(): String {
        return QMUIPackageHelper.getAppVersion(application)
    }

}