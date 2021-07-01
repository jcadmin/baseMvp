package com.joye.jiang.common.sdk.http

import com.lib.common.network.http.HttpConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class RetrofitHttpManager private constructor() {

    private var mRetrofitMap: HashMap<String, Retrofit> = hashMapOf()

    private val builder = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
        .cookieJar(HttpConfig.mCookieJar!!)

    init {
        HttpConfig.mInterceptors?.forEach {
            builder.addInterceptor(it)
        }
    }

    fun <T> create(clazz: Class<T>): T {
        return create(HttpConfig.mBaseUrl, clazz)
    }

    fun <T> create(baseUrl: String?, clazz: Class<T>): T {
        when {
            //当传入的域名为空时
            baseUrl.isNullOrEmpty() -> {
                throw  NullPointerException()
            }
            //当域名已经创建时
            mRetrofitMap.containsKey(baseUrl) -> {
                return mRetrofitMap[baseUrl]!!.create(clazz)
            }
            else -> {
                val retrofit = createRetrofit(baseUrl)
                mRetrofitMap[baseUrl] = retrofit
                return retrofit.create(clazz)
            }
        }
    }

    private fun createRetrofit(baseUrl: String?): Retrofit {
        if (baseUrl.isNullOrEmpty()) {
            throw  NullPointerException()
        } else {
            return Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
        }
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitHttpManager()
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
