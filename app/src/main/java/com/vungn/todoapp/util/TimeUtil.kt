package com.vungn.todoapp.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object TimeUtil {
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

    fun formatToISO8601(date: Date): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss",
            Locale.getDefault(Locale.Category.FORMAT)).format(date)
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(date)
    }

    fun formatFromISO8601ToDay(time: Any): String? {
        return when (time) {
            is String -> {
                val fd = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss",
                        Locale.getDefault(Locale.Category.FORMAT))
                } else {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
                }
                val date = Calendar.getInstance().apply {
                    this.time = fd.parse(time) as Date
                }.time
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    SimpleDateFormat("yyyy-MM-dd",
                        Locale.getDefault(Locale.Category.FORMAT)).format(date)
                } else {
                    SimpleDateFormat("yyyy-MM-dd").format(date)
                }
            }
            is Date -> {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    SimpleDateFormat("yyyy-MM-dd",
                        Locale.getDefault(Locale.Category.FORMAT)).format(time)
                } else {
                    SimpleDateFormat("yyyy-MM-dd").format(time)
                }
            }
            else -> null
        }
    }

    fun getCurrentDateTitle(): String {
        val currentDate = Calendar.getInstance().time
        val day = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("dd",
                Locale.getDefault(Locale.Category.FORMAT)).format(currentDate)
        } else {
            SimpleDateFormat("dd").format(currentDate)
        }
        val month = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("MMMM",
                Locale.getDefault(Locale.Category.FORMAT)).format(currentDate)
        } else {
            SimpleDateFormat("MMMM").format(currentDate)
        }
        val year = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy",
                Locale.getDefault(Locale.Category.FORMAT)).format(currentDate)
        } else {
            SimpleDateFormat("yyyy").format(currentDate)
        }
        return "$month $day, $year"
    }
}