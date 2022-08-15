package com.vungn.todoapp.ui.main.setting.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vungn.todoapp.data.model.User

object SettingBindingAdapter {
    @BindingAdapter("app:srcUrl")
    @JvmStatic
    fun ImageView.srcUrl(
        user: User?
    ) {
        Picasso.get().load(user?.avatar).into(this)
    }
}