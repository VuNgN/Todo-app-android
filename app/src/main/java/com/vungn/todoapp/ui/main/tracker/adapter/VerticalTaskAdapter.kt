package com.vungn.todoapp.ui.main.tracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.databinding.ItemVerticalTaskNormalBinding
import com.vungn.todoapp.databinding.ItemVerticalTaskSelectedBinding

class VerticalTaskAdapter(private val onItemClickListener: ((Task) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var data: List<Task>

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
                holder.handleEvent(data[position])
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

    fun setData(data: List<Task>) {
        this.data = data
    }

    inner class ViewHolderSelectedTask(private val binding: ItemVerticalTaskSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.itemHorizontalTask.apply {
                this.task = task
            }
        }

        fun handleEvent(task: Task) {
            binding.itemHorizontalTask.apply {
                checkButton.setOnClickListener {
                    onItemClickListener?.invoke(task)
                }
            }
        }
    }

    inner class ViewHolderNormalTask(private val binding: ItemVerticalTaskNormalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                this.task = task
            }
        }
    }
}