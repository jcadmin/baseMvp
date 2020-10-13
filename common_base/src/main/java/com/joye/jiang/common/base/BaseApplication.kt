package com.joye.jiang.common.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.joye.jiang.common.data.MemoryConstants
import com.joye.jiang.common.data.NetConstant
import com.joye.jiang.common.sdk.AppManager
import com.joye.jiang.common.sdk.ApplicationUtils
import com.joye.jiang.common.sdk.RealmUtils
import com.joye.jiang.common.sdk.RouterUtil
import com.joye.jiang.common.sdk.http.RetrofitUtils
import com.joye.jiang.imageloader.manager.ImageLoaderManager

abstract class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        RouterUtil.initRouter(this, BuildConfig.DEBUG)
        ApplicationUtils.instance.init(this)
        RealmUtils.initApplication(this)
        initHttp()
        ImageLoaderManager.instance.init(this, MemoryConstants.IMAGE_CACHE_DIR)
    }

    protected fun initHttp() {
        RetrofitUtils.init(this, NetConstant.BASE_DOMAIN)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        AppManager.instance.addActivity(p0)
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
        AppManager.instance.removeActivity(p0)
    }
}