package com.vungn.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.TaskBinding
import com.vungn.todoapp.model.Task

class MainTaskRecycleViewAdapter : RecyclerView.Adapter<MainTaskRecycleViewAdapter.ViewHolder>() {
    private lateinit var data: MutableList<Task>

    class ViewHolder(private val binding: TaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, position: Int) {
            setupFirstItem(position)
            binding.title.text = task.title
            binding.time.text = task.time
            binding.description.text = task.description
            task.users.forEachIndexed { index, user ->
                when (index) {
                    0 -> {
                        binding.profileImage1.visibility = View.VISIBLE
                        Picasso.get().load(user.avatar).into(binding.profileImage1)
                    }
                    1 -> {
                        binding.profileImage2.visibility = View.VISIBLE
                        Picasso.get().load(user.avatar).into(binding.profileImage2)
                    }
                    2 -> {
                        binding.profileImage3.visibility = View.VISIBLE
                        Picasso.get().load(user.avatar).into(binding.profileImage3)
                    }
                    3 -> {
                        binding.profileImage4.visibility = View.VISIBLE
                        Picasso.get().load(user.avatar).into(binding.profileImage4)
                    }
                }
            }
        }

        fun setupFirstItem(position: Int) {
            if (position == 0) {
                val lp = LinearLayout.LayoutParams(
                    binding.root.resources.getDimensionPixelSize(R.dimen.task_width),
                    binding.root.resources.getDimensionPixelSize(R.dimen.task_height)
                )
                lp.setMargins(
                    binding.root.resources.getDimensionPixelSize(R.dimen.edge_horizontal),
                    0,
                    binding.root.resources.getDimensionPixelSize(R.dimen.task_margin_end),
                    0
                )
                binding.parent.layoutParams = lp
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    fun addData(data: MutableList<Task>) {
        this.data = data
    }
}