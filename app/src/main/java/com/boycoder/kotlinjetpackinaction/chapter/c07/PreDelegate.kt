package com.boycoder.kotlinjetpackinaction.chapter.c07

import android.content.SharedPreferences
import com.boycoder.kotlinjetpackinaction.App
import kotlin.reflect.KProperty

class PreDelegate<T>(
        private val name: String,
        private val default: T,
        private val isCommit: Boolean = false,
        private val prefs: SharedPreferences = App.prefs) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getPref(name, default) ?: default
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        value?.let {
            putPref(name, value)
        }
    }

    private fun <T> getPref(name: String, default: T): T? = with(prefs) {
        val result: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type is not supported")
        }

        result as? T
    }

    private fun <T> putPref(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type is not supported")
        }

        if (isCommit) {
            commit()
        } else {
            apply()
        }
    }
}