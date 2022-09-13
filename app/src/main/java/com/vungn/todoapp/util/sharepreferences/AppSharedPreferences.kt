@file:Suppress("unused")

package com.vungn.todoapp.util.sharepreferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

@Suppress("unused")
class AppSharedPreferences (
    private val preferences: SharedPreferences,
) : SharedPreferences by preferences {

    constructor(
        context: Context,
        name: String,
    ) : this(context.getSharedPreferences(name, Context.MODE_PRIVATE))

    fun getString(key: String): String? = getString(key, null)

    fun getStringSet(key: String): MutableSet<String>? = getStringSet(key, null)

    fun clears(vararg names: String) {
        applyChanges {
            names.forEach { remove(it) }
        }
    }

    inline fun clear(predicate: (String) -> Boolean) {
        applyChanges {
            all.keys.forEach {
                if (predicate(it)) remove(it)
            }
        }
    }
}

inline fun SharedPreferences.applyChanges(
    changes: SharedPreferences.Editor.() -> Unit,
) {
    edit().run {
        changes()
        apply()
    }
}

inline fun SharedPreferences.commitChanges(
    changes: SharedPreferences.Editor.() -> Unit,
) {
    edit().run {
        changes()
        commit()
    }
}

fun SharedPreferences.save(key: String, value: String?) {
    edit().putString(key, value).apply()
}

fun SharedPreferences.save(key: String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.save(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.save(key: String, value: Float) {
    edit().putFloat(key, value).apply()
}

fun SharedPreferences.save(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun SharedPreferences.delete(key: String) {
    edit().remove(key).apply()
}

fun SharedPreferences.clear() {
    edit().clear().apply()
}
