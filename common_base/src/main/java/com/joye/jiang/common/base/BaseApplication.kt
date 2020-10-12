package com.joye.jiang.common.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.joye.jiang.common.sdk.AppManager
import com.joye.jiang.common.sdk.ApplicationUtils
import com.joye.jiang.common.sdk.RealmUtils

class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {
    override fun onCreate() {
        super.onCreate()
        ApplicationUtils.instance.init(this)
        RealmUtils.init(this)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        AppManager.getAppManager().addActivity(p0)
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
        AppManager.getAppManager().removeActivity(p0)
    }
}