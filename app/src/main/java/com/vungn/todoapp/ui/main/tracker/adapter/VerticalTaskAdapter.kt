package com.vungn.todoapp.ui.main.tracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.databinding.ItemVerticalTaskNormalBinding
import com.vungn.todoapp.databinding.ItemVerticalTaskSelectedBinding

class VerticalTaskAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var data: List<Task>
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolderSelectedTask(
                ItemVerticalTaskSelectedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                mListener!!
            )
            else -> ViewHolderNormalTask(
                ItemVerticalTaskNormalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                mListener!!
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSelectedTask -> {
                val task = data[position]
                holder.bind(task)
                holder.handleEvent(task)
            }
            is ViewHolderNormalTask -> {
                val task = data[position]
                holder.bind(task)
                holder.handleEvent(task)
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

    inner class ViewHolderSelectedTask(
        private val binding: ItemVerticalTaskSelectedBinding,
        private val listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.itemHorizontalTask.apply {
                this.task = task
            }
        }

        fun handleEvent(task: Task) {
            itemView.setOnClickListener {
                listener.onItemClick(task)
            }
        }
    }

    inner class ViewHolderNormalTask(
        private val binding: ItemVerticalTaskNormalBinding,
        private val listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                this.task = task
            }
        }

        fun handleEvent(task: Task) {
            itemView.setOnClickListener {
                listener.onItemClick(task)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}