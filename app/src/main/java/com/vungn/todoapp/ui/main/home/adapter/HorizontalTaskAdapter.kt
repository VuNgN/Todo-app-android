package com.vungn.todoapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.databinding.ItemHorizontalTaskBinding

class HorizontalTaskAdapter :
    RecyclerView.Adapter<HorizontalTaskAdapter.ViewHolder>() {
    private lateinit var data: List<Task>
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemHorizontalTaskBinding.inflate(inflater, parent, false)
        itemView.root.layoutParams.width =
            itemView.root.resources.getDimensionPixelOffset(R.dimen.task_width)
        return ViewHolder(itemView, mListener!!)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = data[position]
        holder.bind(task)
        holder.handleEvent(task)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Task>) {
        this.data = data
    }

    fun notifyChange(firstItemVisible: Int, lastItemVisible: Int) {
        notifyItemRangeChanged(firstItemVisible, lastItemVisible)
    }

    inner class ViewHolder(
        private val binding: ItemHorizontalTaskBinding,
        private val listener: OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                this.task = task
                executePendingBindings()
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