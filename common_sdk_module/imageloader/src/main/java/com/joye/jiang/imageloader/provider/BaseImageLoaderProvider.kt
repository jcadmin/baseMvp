package com.joye.jiang.imageloader.provider

import android.app.Fragment
import android.content.Context
import com.joye.jiang.imageloader.imgconfig.ImageLoader

abstract class BaseImageLoaderProvider(context: Context?) {
    /**
     * 初始化配置
     */
    protected abstract fun initConfigs(context: Context?)

    /**
     * 释放图片占有的内存
     *
     * @param context
     */
    abstract fun releaseMemoryCache(context: Context)

    /**
     * 加载图片
     *
     * @param context  上下文
     * @param loader   图片加载对象
     * @param listener 图片加载监听
     */
    abstract fun loadImage(
        context: Context,
        loader: ImageLoader<*>
    )

    abstract fun loadAvatar(
        context: Context,
        loader: ImageLoader<*>
    )

    /**
     * 加载图片
     *
     * @param fragment 所依附的fragment
     * @param loader   图片加载对象
     * @param listener 图片加载监听
     */
    abstract fun loadImage(
        fragment: Fragment,
        loader: ImageLoader<*>
    )

    /**
     * 初始化
     */
    init {
        initConfigs(context)
    }
}