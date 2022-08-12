package com.vungn.todoapp.ui.main.tracker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.ItemCalendarBinding

class HorizontalCalendarAdapter(context: Context, private val itemWidth: Int) :
    RecyclerView.Adapter<HorizontalCalendarAdapter.ViewHolder>() {
    private lateinit var data: MutableList<String>
    private val context: Context
    private var selectedPosition: Int = 3

    init {
        this.context = context
    }

    class ViewHolder(private val context: Context, binding: ItemCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemCalendarBinding

        init {
            this.binding = binding
        }

        fun bind(dayOfMonth: String, position: Int, selectedPosition: Int) {
            val dayOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            binding.dayOfWeek.text = dayOfWeek[position]
            binding.dayOfMonth.text = dayOfMonth
            if (position == selectedPosition) {
                binding.dayOfWeek.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.primary,
                        null
                    )
                )
                binding.dayOfMonth.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.primary,
                        null
                    )
                )
            } else {
                binding.dayOfWeek.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.text_gray_tracker,
                        null
                    )
                )
                binding.dayOfMonth.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.text_gray_tracker,
                        null
                    )
                )
            }
        }

        fun onClickViewItem(selectedPosition: () -> Unit, notifyItemChanged: () -> Unit) {
            this.itemView.setOnClickListener {
                selectedPosition()
                notifyItemChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemCalendarBinding.inflate(inflater, parent, false)

        itemView.root.layoutParams.width = itemWidth

        return ViewHolder(context, itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dayOfMonth = data[position]
        holder.onClickViewItem({ setSelectedPosition(position) }, { notifyDataSetChanged() })
        holder.bind(dayOfMonth, position, selectedPosition)
    }

    override fun getItemCount(): Int = data.size

    private fun setSelectedPosition(position: Int) {
        this.selectedPosition = position
    }

    fun setData(data: MutableList<String>) {
        this.data = data
    }
}