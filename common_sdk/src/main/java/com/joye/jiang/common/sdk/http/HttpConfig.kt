package com.lib.common.network.http

import okhttp3.CookieJar
import okhttp3.Interceptor
import java.util.*

object HttpConfig {
    internal var mBaseUrl: String? = null
    internal var mInterceptors: MutableList<Interceptor>? = null
    internal var mCookieJar: CookieJar? = null

    class Builder {
        private val mHttpConfig: HttpConfig = HttpConfig

        fun baseUrl(baseUrl: String): Builder {
            mHttpConfig.mBaseUrl = baseUrl
            return this
        }

        fun interceptors(interceptor: Interceptor): Builder {
            if (mHttpConfig.mInterceptors == null) {
                mHttpConfig.mInterceptors = ArrayList()
            }
            mHttpConfig.mInterceptors!!.add(interceptor)
            return this
        }

        fun cookie(cookieJar: CookieJar): Builder {
            mHttpConfig.mCookieJar = cookieJar
            return this
        }

        fun build(): HttpConfig {
            return mHttpConfig
        }
    }

}
