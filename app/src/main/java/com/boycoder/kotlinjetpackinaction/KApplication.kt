package com.boycoder.kotlinjetpackinaction

import android.app.Application

/**
 * Created by zhu.tao on 2020/8/6.
 */
class KApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        App.context = this
    }
}