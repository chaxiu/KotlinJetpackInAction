package com.boycoder.kotlinjetpackinaction.chapter.c07

import android.content.SharedPreferences
import com.boycoder.kotlinjetpackinaction.App
import kotlin.reflect.KProperty

class PreferenceString(
        private val name: String,
        private val default:String ="",
        private val isCommit: Boolean = false,
        private val prefs: SharedPreferences = App.prefs) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return prefs.getString(name, default) ?: default
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        with(prefs.edit()){
            putString(name, value)
            if (isCommit) {
                commit()
            } else {
                apply()
            }
        }
    }
}