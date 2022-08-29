package com.vungn.todoapp.ui.main.tracker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.ItemCalendarBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HorizontalCalendarAdapter(
    context: Context,
    private val itemWidth: Int,
    selectedDate: Date?,
) :
    RecyclerView.Adapter<HorizontalCalendarAdapter.ViewHolder>() {
    private lateinit var data: MutableList<Date>
    private val context: Context
    private var selectedItem = (Calendar.getInstance().apply {
        if (selectedDate != null) {
            time = selectedDate
        }
    }[Calendar.DAY_OF_MONTH] - 1)
    private var mListener: AdapterView.OnItemClickListener? = null

    init {
        this.context = context
    }

    class ViewHolder(
        private val context: Context, binding: ItemCalendarBinding,
        private val listener: AdapterView.OnItemClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemCalendarBinding

        init {
            this.binding = binding
        }

        fun bind(position: Int, selectedPosition: Int, data: MutableList<Date>) {
            val sdf = SimpleDateFormat("EEE", Locale.ENGLISH)
            val cal = Calendar.getInstance()
            cal.time = data[position]

            try {
                binding.dayOfWeek.text = sdf.format(cal.time).toString()
            } catch (ex: ParseException) {
                Log.v("Exception", ex.localizedMessage!!)
            }
            binding.dayOfMonth.text = cal[Calendar.DAY_OF_MONTH].toString()

            if (selectedPosition == position)
                makeItemSelected()
            else {
                makeItemDefault()
            }
        }

        fun onClickViewItem(
            position: Int,
            setSelectedPosition: () -> Unit,
            notifyItemChanged: () -> Unit,
        ) {
            itemView.setOnClickListener {
                setSelectedPosition()
                listener.onItemClick(null, null, position, 1)
                notifyItemChanged()
            }
        }

        private fun makeItemSelected() {
            binding.dayOfMonth.setTextColor(ContextCompat.getColor(context,
                R.color.primary_variant))
            binding.dayOfWeek.setTextColor(ContextCompat.getColor(context, R.color.primary_variant))
            itemView.isEnabled = false
        }

        private fun makeItemDefault() {
            binding.dayOfMonth.setTextColor(ContextCompat.getColor(context,
                R.color.text_gray_tracker))
            binding.dayOfWeek.setTextColor(ContextCompat.getColor(context,
                R.color.text_gray_tracker))
            itemView.isEnabled = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemCalendarBinding.inflate(inflater, parent, false)

        itemView.root.layoutParams.width = itemWidth

        return ViewHolder(context, itemView, mListener!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onClickViewItem(position, { setSelectedPosition(position) },
            { notifyDataSetChanged() })
        holder.bind(position, selectedItem, data)
    }

    override fun getItemCount(): Int = data.size

    private fun setSelectedPosition(position: Int) {
        this.selectedItem = position
    }

    fun setData(data: MutableList<Date>) {
        this.data = data
    }

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    }

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        mListener = listener
    }
}