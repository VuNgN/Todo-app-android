package com.vungn.todoapp.ui.main.searchuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.RowSearchTextviewBinding

class SearchUserAdapter constructor(
    private var lstSearch: List<UserResponse>,
) :
    RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = RowSearchTextviewBinding.inflate(inflater, parent, false)

        return ViewHolder(itemView, mListener!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = lstSearch[position]
        holder.bind(task)
        holder.handleEvent(task)
    }

    override fun getItemCount(): Int = lstSearch.size

    inner class ViewHolder(
        private val binding: RowSearchTextviewBinding,
        private val listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserResponse) {
            binding.apply {
                this.user = user
                executePendingBindings()
            }
        }

        fun handleEvent(user: UserResponse) {
            itemView.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: UserResponse)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

}