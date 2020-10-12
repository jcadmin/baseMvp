package com.joye.jiang.common.base

import com.joye.jiang.common.sdk.RouterUtil
import com.trello.rxlifecycle3.components.support.RxFragment


class BaseFragment : RxFragment() {

    init {
        RouterUtil.inject(this)
    }

}