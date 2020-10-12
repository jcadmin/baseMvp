package com.joye.jiang.common.base.activity

import android.os.Bundle
import com.joye.jiang.common.sdk.RouterUtil
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

abstract class AbstractActivity : RxAppCompatActivity() {
    init {
        RouterUtil.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}