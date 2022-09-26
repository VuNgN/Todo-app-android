package com.vungn.todoapp.ui.main.adduser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.ItemUserBinding

class AddUserRecycleViewAdapter constructor(
    private val lstUser: List<UserResponse>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemUserBinding = ItemUserBinding.inflate(layoutInflater)
        return UserInListHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userResponse: UserResponse = lstUser.get(position)

        val userInListHolder: UserInListHolder = holder as UserInListHolder
        userInListHolder.itemUserBinding.nameTextview.text = userResponse.name
        userInListHolder.itemUserBinding.emailTextview.text = userResponse.email
//            Glide.with(context).load(userResponse.avatar).into(userInListHolder.itemUserBinding.avartarImageView)
    }

    override fun getItemCount(): Int {
        return lstUser.size
    }

    inner class UserInListHolder constructor(val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

}
