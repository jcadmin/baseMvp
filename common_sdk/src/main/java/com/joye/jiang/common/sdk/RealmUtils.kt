package com.joye.jiang.common.sdk

import android.app.Application
import io.realm.Realm

class RealmUtils {

    companion object {
        @JvmStatic
        fun init(application: Application) {
            Realm.init(application)
        }
    }

}