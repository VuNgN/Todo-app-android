package com.vungn.todoapp.ui.main.searchuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.model.request.UserRequest
import com.vungn.todoapp.databinding.ItemUserBinding
import com.vungn.todoapp.databinding.ItemUserSelectedBinding

class ChoiceUserAdapter(private val lstUser: List<UserResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return SELECTED
        } else return UNSELECTED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        val binding1: ItemUserBinding = ItemUserBinding.inflate(layoutInflater)
        val binding2: ItemUserSelectedBinding = ItemUserSelectedBinding.inflate(layoutInflater)

        if (viewType == UNSELECTED) {
            return UserInListHolder(binding1)
        } else {
            return UserSelectedHolder(binding2)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userResponse: UserResponse
    }

    override fun getItemCount(): Int {
        return lstUser.size
    }

    inner class UserSelectedHolder constructor(itemUserSelectedBinding: ItemUserSelectedBinding) :
        RecyclerView.ViewHolder(itemUserSelectedBinding.root)

    inner class UserInListHolder constructor(itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

    companion object {
        private val SELECTED = 1
        private val UNSELECTED = 2
    }

}
