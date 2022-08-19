package com.vungn.todoapp.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    @SuppressLint("SimpleDateFormat")
    fun formatFromISO8601ToHour(startOn: String): String {
        val fd = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss",
                Locale.getDefault(Locale.Category.FORMAT))
        } else {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
        }
        val date = Calendar.getInstance().apply {
            time = fd.parse(startOn) as Date
        }.time
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("hh:mm a",
                Locale.getDefault(Locale.Category.FORMAT)).format(date)
        } else {
            SimpleDateFormat("hh:mm a").format(date)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatToISO8601(date: Date): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss",
            Locale.getDefault(Locale.Category.FORMAT)).format(date)
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(date)
    }
}