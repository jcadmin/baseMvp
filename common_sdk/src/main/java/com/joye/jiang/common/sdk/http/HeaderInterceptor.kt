package and.zifeiyu.meet.net.http.interceptor

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 请求头部信息拦截器
 */

open class HeaderInterceptor : Interceptor {
    var mHeaders: HashMap<String, String>? = null

    constructor(headers: HashMap<String, String>) {
        mHeaders = headers
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var headers = HashMap<String, String>()
        /*if (!TextUtils.isEmpty(AccountInfoManager.instance.userModel.token)) {
            headers["accessToken"] = AccountInfoManager.instance.userModel.token!!
        }*/
        if (mHeaders != null && mHeaders!!.isNotEmpty()) {
            headers.putAll(mHeaders!!)
        }
        val request = chain.request().newBuilder()
            .headers(headers.toHeaders())
            .build()
        return chain.proceed(request)
    }
}
