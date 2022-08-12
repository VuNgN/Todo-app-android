package com.vungn.todoapp.ui.main.setting.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vungn.todoapp.model.User

object SrcUrl {
    @BindingAdapter("srcUrl")
    @JvmStatic
    fun srcUrl(
        imageView: ImageView,
        user: User?
    ) {
        Picasso.get().load(user?.avatar).into(imageView)
    }
}