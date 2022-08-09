package com.vungn.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vungn.todoapp.databinding.ItemVerticalTaskNormalBinding
import com.vungn.todoapp.databinding.ItemVerticalTaskSelectedBinding
import com.vungn.todoapp.model.Task

class VerticalTaskAdapter(private val onItemClickListener: (() -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var data: MutableList<Task>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolderSelectedTask(
                ItemVerticalTaskSelectedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ViewHolderNormalTask(
                ItemVerticalTaskNormalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSelectedTask -> {
                holder.bind(data[position])
                holder.handleEvent()
            }
            is ViewHolderNormalTask -> {
                holder.bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(data: MutableList<Task>) {
        this.data = data
    }

    inner class ViewHolderSelectedTask(private val binding: ItemVerticalTaskSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.itemHorizontalTask.apply {
                title.text = task.title
                time.text = task.time
                description.text = task.description
                task.users.forEachIndexed { index, user ->
                    when (index) {
                        0 -> {
                            profileImage1.visibility = View.VISIBLE
                            Picasso.get().load(user.avatar).into(profileImage1)
                        }
                        1 -> {
                            profileImage2.visibility = View.VISIBLE
                            Picasso.get().load(user.avatar).into(profileImage2)
                        }
                        2 -> {
                            profileImage3.visibility = View.VISIBLE
                            Picasso.get().load(user.avatar).into(profileImage3)
                        }
                        3 -> {
                            profileImage4.visibility = View.VISIBLE
                            Picasso.get().load(user.avatar).into(profileImage4)
                        }
                    }
                }
            }
        }

        fun handleEvent() {
            binding.itemHorizontalTask.apply {
                checkButton.setOnClickListener {
                    onItemClickListener?.invoke()
                }
            }
        }
    }

    inner class ViewHolderNormalTask(private val binding: ItemVerticalTaskNormalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                title.text = task.title
                time.text = task.time
                description.text = task.description
            }
        }
    }
}