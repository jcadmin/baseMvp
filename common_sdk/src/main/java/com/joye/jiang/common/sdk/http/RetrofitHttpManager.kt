package com.joye.jiang.common.sdk.http

import android.text.TextUtils
import com.lib.common.network.http.HttpConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


class RetrofitHttpManager private constructor() {

    private var mRetrofit: Retrofit? = null

    init {

        val builder = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .cookieJar(HttpConfig.mCookieJar!!)
        HttpConfig.mInterceptors?.forEach {
            builder.addInterceptor(it)
        }

        if (!TextUtils.isEmpty(HttpConfig.mBaseUrl)) {
            mRetrofit = Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .baseUrl(HttpConfig.mBaseUrl!!)
                .build()
        }
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitHttpManager()
    }

    fun <T> create(clazz: Class<T>): T {
        return mRetrofit!!.create(clazz)
    }

    companion object {
        var DEFAULT_TIME_OUT = 5
        var DEFAULT_READ_TIME_OUT = 10
        var DEFAULT_WRITE_TIME_OUT = 10

        @JvmStatic
        val instance: RetrofitHttpManager
            get() = SingletonHolder.INSTANCE
    }
}
