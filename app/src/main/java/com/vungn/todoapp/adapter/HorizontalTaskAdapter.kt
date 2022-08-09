package com.vungn.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.ItemHorizontalTaskBinding
import com.vungn.todoapp.model.Task

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
    }
}