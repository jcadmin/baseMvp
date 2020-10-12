package com.joye.jiang.common.base.activity

import android.os.Bundle
import com.joye.jiang.common.base.BasePresenter
import com.joye.jiang.common.base.BaseView

abstract class MVPActivity : AbstractActivity() {

    protected var presenters = mutableListOf<BasePresenter<BaseView>>()

    /**
     * 初始化presenter
     */
    abstract fun initPresenter(): MutableList<BasePresenter<BaseView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenters = initPresenter()
        presenters.forEach {
            it.onCreate()
        }
    }

    override fun onStart() {
        super.onStart()
        presenters.forEach {
            it.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        presenters.forEach {
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        presenters.forEach {
            it.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        presenters.forEach {
            it.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenters.forEach {
            it.onDestroy()
        }
    }


}