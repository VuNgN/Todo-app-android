package com.vungn.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vungn.todoapp.databinding.TaskBinding
import com.vungn.todoapp.model.Task

class MainTaskRecycleViewAdapter(context: Context) :
    RecyclerView.Adapter<MainTaskRecycleViewAdapter.ViewHolder>() {
    private lateinit var data: MutableList<Task>
    private val context: Context

    init {
        this.context = context
    }

    class ViewHolder(context: Context, private val binding: TaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context: Context

        init {
            this.context = context
        }

        fun bind(task: Task) {
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            context,
            TaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

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
}