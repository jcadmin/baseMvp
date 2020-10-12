package com.joye.jiang.common.sdk.http

import java.io.Serializable

/**
 * Created by Admin
 * Http response 返回格式
 */

open class HttpResponse<T> : Serializable {
    companion object {
        const val RESPONSE_STATUS_SUCCESS = 0
    }

    @JvmField
    var code: Int = 0
    @JvmField
    var msg: String? = null
    @JvmField
    var data: T? = null

    fun isSuccess(): Boolean {
        return code == RESPONSE_STATUS_SUCCESS
    }
}
