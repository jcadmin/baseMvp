package com.joye.jiang.common.sdk.http

/**
 * ServerException发生后，将自动转换为ResponeThrowable返回
 */
open class ServerException(
    var code: Int?,
    override var message: String?,
    var data: HttpResponse<Any>?
) :
    RuntimeException()