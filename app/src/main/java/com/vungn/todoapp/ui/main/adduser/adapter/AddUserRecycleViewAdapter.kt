package com.vungn.todoapp.ui.main.adduser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.ItemUserBinding

class AddUserRecycleViewAdapter :
    RecyclerView.Adapter<AddUserRecycleViewAdapter.UserInListHolder>() {
    private val users: MutableList<UserResponse> = mutableListOf()

    private var listener: OnItemClickListener? = null

    fun addList(list: List<UserResponse>) {
        users.clear()
        users.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return UserInListHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserInListHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.handleEvent(position)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UserInListHolder(
        private val binding: ItemUserBinding,
        private val listener: OnItemClickListener?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userResponse: UserResponse) {
            binding.apply {
                nameTextview.text = userResponse.name
                emailTextview.text = userResponse.email
                Glide.with(itemView.context).load(userResponse.avatar)
                    .into(avartarImageView)
                executePendingBindings()
            }
        }

        fun handleEvent(index: Int) {
            itemView.setOnClickListener {
                listener?.onDelete(index)
            }
        }

    }

    interface OnItemClickListener {
        fun onDelete(index: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
