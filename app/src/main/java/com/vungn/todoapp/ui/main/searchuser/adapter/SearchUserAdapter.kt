package com.vungn.todoapp.ui.main.searchuser.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.RowSearchTextviewBinding

class SearchUserAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListener: OnItemClickListener? = null
    private var list: MutableList<UserResponse> = mutableListOf()
    private var listSelected: MutableList<Int> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        if (listSelected.size != 0) {
            for (index in listSelected) {
                if (position == index) {
                    return SELECTED
                }
            }
        }
        return UNSELECTED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = RowSearchTextviewBinding.inflate(inflater, parent, false)

        if (viewType == UNSELECTED) {
            return ViewHolderUnSelected(itemView, mListener!!)
        } else {
            return ViewHolderSelected(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = list[position]

        if (holder is ViewHolderUnSelected) {
            val unSelected: ViewHolderUnSelected = holder
            unSelected.bindUnSelected(user)
            unSelected.handleEvent(user)
        } else {
            val selected: ViewHolderSelected = holder as ViewHolderSelected
            selected.bindSelected(user)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolderUnSelected(
        private val binding: RowSearchTextviewBinding,
        private val listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bindUnSelected(user: UserResponse) {
            binding.apply {
                this.user = user
                binding.rowItemNameSearch.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
                executePendingBindings()
            }
        }

        fun handleEvent(user: UserResponse) {
            itemView.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    inner class ViewHolderSelected(
        private val binding: RowSearchTextviewBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bindSelected(user: UserResponse) {
            binding.apply {
                this.user = user
                binding.rowItemNameSearch.setTextColor(ContextCompat.getColor(itemView.context,R.color.light_blue_A400))
                executePendingBindings()
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(user: UserResponse)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun addList(listsearch: List<UserResponse>) {
        list.clear()
        list.addAll(listsearch)
    }

    fun addList(listSearch: List<UserResponse>, listOld: List<UserResponse>) {
        list.clear()
        listSelected.clear()
        var index = 0
        for (userSearch in listSearch) {
            var temp = 0
            for (user in listOld) {
                if (user == userSearch) {
                    temp++
                }
            }
            if (temp != 0) {
                listSelected.add(index)
            }
            list.add(userSearch)
            index++
        }
    }

    companion object {
        private val SELECTED = 0
        private val UNSELECTED = 1
        private val TAG = "SearchUserAdapter"
    }


}
