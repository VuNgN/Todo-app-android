package com.vungn.todoapp.common.binding

import android.view.View
import androidx.databinding.BindingAdapter

object ClickGuardBindingAdapter {

    @BindingAdapter("onSafeClick")
    @JvmStatic
    fun bindOnSafeClickListener(view: View, delegate: View.OnClickListener?) {

        view.setOnClickListener(delegate)
    }
}