package com.vungn.todoapp.util.extention

import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

object CommonEx {
    fun FragmentContainerView.popupAndGo(popup: Int, aim: Int) {
        val navOptions = NavOptions.Builder().setPopUpTo(popup, true).build()
        Navigation.findNavController(this).navigate(aim, null, navOptions)
    }
}