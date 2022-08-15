package com.vungn.todoapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.ItemHorizontalTaskBinding
import com.vungn.todoapp.data.model.Task

class HorizontalTaskAdapter :
    RecyclerView.Adapter<HorizontalTaskAdapter.ViewHolder>() {
    private lateinit var data: MutableList<Task>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemHorizontalTaskBinding.inflate(inflater, parent, false)
        itemView.root.layoutParams.width =
            itemView.root.resources.getDimensionPixelOffset(R.dimen.task_width)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: MutableList<Task>) {
        this.data = data
    }

    fun notifyChange(firstItemVisible: Int, lastItemVisible: Int) {
        notifyItemRangeChanged(firstItemVisible, lastItemVisible)
    }

    inner class ViewHolder(private val binding: ItemHorizontalTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                this.task = task
                executePendingBindings()
            }
        }
    }
}