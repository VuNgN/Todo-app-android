package com.vungn.todoapp.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vungn.todoapp.model.User

object TaskBindingAdapter {
    @BindingAdapter("imageSource")
    @JvmStatic
    fun imageSource(
        imageView: de.hdodenhof.circleimageview.CircleImageView,
        user: User?
    ) {
        imageView.visibility = if (user != null) View.VISIBLE else View.GONE
        Picasso.get().load(user?.avatar).into(imageView)
    }
}