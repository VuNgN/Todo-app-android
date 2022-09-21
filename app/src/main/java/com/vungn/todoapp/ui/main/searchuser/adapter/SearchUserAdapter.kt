package com.vungn.todoapp.ui.main.searchuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.ItemUserBinding
import com.vungn.todoapp.databinding.ItemUserSelectedBinding

class SearchUserAdapter constructor(
    private val context: Context,
    private val lstUser: List<UserResponse>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 1) {
            SELECTED
        } else UNSELECTED
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
        val userResponse: UserResponse = lstUser.get(position)
        if (holder is UserSelectedHolder) {
            holder.itemUserSelectedBinding.userSelected
            holder.itemUserSelectedBinding.nameTextview.text = userResponse.name
            holder.itemUserSelectedBinding.emailTextview.text = userResponse.email

            Glide.with(context).load(userResponse.avatar)
                .into(holder.itemUserSelectedBinding.avartarImageView)
        } else {
            val userInListHolder: UserInListHolder = holder as UserInListHolder
            userInListHolder.itemUserBinding.nameTextview.text = userResponse.name
            userInListHolder.itemUserBinding.emailTextview.text = userResponse.email

            Glide.with(context).load(userResponse.avatar)
                .into(userInListHolder.itemUserBinding.avartarImageView)
        }
    }

    override fun getItemCount(): Int {
        return lstUser.size
    }

    inner class UserSelectedHolder constructor(val itemUserSelectedBinding: ItemUserSelectedBinding) :
        RecyclerView.ViewHolder(itemUserSelectedBinding.root)

    inner class UserInListHolder constructor(val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

    companion object {
        private val SELECTED = 1
        private val UNSELECTED = 2
    }

}
