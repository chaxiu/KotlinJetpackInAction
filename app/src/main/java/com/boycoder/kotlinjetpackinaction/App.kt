package com.boycoder.kotlinjetpackinaction

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by zhu.tao on 2020/8/6.
 */
object App {
    public lateinit var context: Context

    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }
}