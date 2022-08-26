package com.vungn.todoapp.ui.main.tracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentTrackerBinding
import com.vungn.todoapp.ui.main.tracker.adapter.HorizontalCalendarAdapter
import com.vungn.todoapp.ui.main.tracker.adapter.VerticalTaskAdapter
import com.vungn.todoapp.ui.main.tracker.contract.TrackerViewModel
import com.vungn.todoapp.ui.main.tracker.contract.implement.TrackerViewModelImpl
import java.util.*

class TrackerFragment : Fragment() {
    private lateinit var binding: FragmentTrackerBinding
    private lateinit var vm: TrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View = FragmentTrackerBinding.inflate(inflater, container, false).also {
        val factory =
            TrackerViewModelImpl.Factory(this@TrackerFragment.requireActivity().application)
        vm = ViewModelProvider(this, factory)[TrackerViewModelImpl::class.java]
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        handleEvent()
    }

    private fun handleEvent() {
        binding.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddTask.setOnClickListener {
                findNavController().navigate(
                    R.id.action_trackerFragment_to_insertTaskFragment,
                    null
                )
            }
        }
        vm.date().observe(viewLifecycleOwner){
            Log.d("", "handleEvent: $it")
        }
    }

    private fun setupUi() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.calendarRecycleView)
        setUpCalendar()
        setUpTasks()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpTasks() {
        val taskAdapter = VerticalTaskAdapter { task ->
            val action = TrackerFragmentDirections.actionTrackerFragmentToTaskFragment(task)
            findNavController().navigate(action)
        }
        binding.taskRecycleView.adapter = taskAdapter
        taskAdapter.setData(vm.getTasks())
        vm.date().observe(viewLifecycleOwner){
            taskAdapter.setData(vm.getTasks())
            taskAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpCalendar(changeMonth: Calendar? = null) {
        val edge = resources.getDimensionPixelSize(R.dimen.edge_horizontal)
        val itemWidth = (requireContext().resources.displayMetrics.widthPixels - edge * 2) / 7

        val lastDayInCalendar = Calendar.getInstance(Locale.ENGLISH)
        val cal = Calendar.getInstance(Locale.ENGLISH)

        val currentDate = Calendar.getInstance(Locale.ENGLISH)
        val currentDay = currentDate[Calendar.DAY_OF_MONTH]

        var selectedDay: Int = when {
            changeMonth != null -> changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH)
            else -> currentDay
        }

        val dates = ArrayList<Date>()

        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        lastDayInCalendar.add(Calendar.MONTH, 6)


        var currentPosition = 0
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while (dates.size < maxDaysInMonth) {
            if (monthCalendar[Calendar.DAY_OF_MONTH] == selectedDay)
                currentPosition = dates.size
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        binding.apply {
            val adapter =
                HorizontalCalendarAdapter(requireContext(), itemWidth)
            adapter.setData(dates)
            calendarRecycleView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            calendarRecycleView.adapter = adapter
            when {
                currentPosition > 2 -> calendarRecycleView.scrollToPosition(currentPosition - 3)
                maxDaysInMonth - currentPosition < 2 -> calendarRecycleView.scrollToPosition(
                    currentPosition)
                else -> calendarRecycleView.scrollToPosition(currentPosition)
            }
            adapter.setOnItemClickListener(object : HorizontalCalendarAdapter.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    Log.d("", "onItemClick: $position")
                    val clickCalendar = Calendar.getInstance()
                    clickCalendar.time = dates[position]
                    selectedDay = clickCalendar[Calendar.DAY_OF_MONTH]
                    vm.date().postValue(dates[position])
                }
            })
        }
    }
}