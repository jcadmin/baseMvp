package com.joye.jiang.common.sdk

import android.app.Application
import io.realm.*

class RealmUtils private constructor() {

    companion object {
        @JvmStatic
        val instance = SingletonHolder.holder

        @JvmStatic
        private var realmConfiguration: RealmConfiguration? = null

        @JvmStatic
        fun initApplication(application: Application) {
            Realm.init(application)
        }
    }

    private object SingletonHolder {
        val holder = RealmUtils()
    }

    fun initUser(uid: Int) {
        realmConfiguration =
            RealmConfiguration.Builder()
                .name("joye_jiang_$uid.realm")
                .schemaVersion(1) //数据库版本
                .migration(migration)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    // realm数据库版本更新
    var migration = RealmMigration { realm: DynamicRealm, oldVersion: Long, newVersion: Long ->
        // DynamicRealm exposes an editable schema
        val schema = realm.schema

    }
}