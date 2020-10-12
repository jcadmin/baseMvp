package com.joye.jiang.common.sdk.http

import and.zifeiyu.meet.net.http.interceptor.HeaderInterceptor
import android.app.Application
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.lib.common.network.http.HttpConfig
import okhttp3.Cookie
import okhttp3.internal.platform.Platform

object RetrofitUtils {
    var mHttpConfig: HttpConfig? = null
    private var cookieCache: SetCookieCache? = null
    private val cookies: Iterator<Cookie>? = null

    fun init(
        application: Application,
        baseUrl: String = "",
        headers: HashMap<String, String> = hashMapOf()
    ) {
        val headerInterceptor = HeaderInterceptor(headers)
        // cookie
        cookieCache = SetCookieCache()
        val cookieJar: ClearableCookieJar = PersistentCookieJar(
            cookieCache,
            SharedPrefsCookiePersistor(application)
        )
        val loggingInterceptor: LoggingInterceptor.Builder = LoggingInterceptor.Builder()
        loggingInterceptor.tag("joyehttp")
        loggingInterceptor
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
        mHttpConfig = HttpConfig.Builder()
            .baseUrl(baseUrl)
            .interceptors(loggingInterceptor.build())
            .interceptors(headerInterceptor)
            .cookie(cookieJar)
            .build()
    }
}