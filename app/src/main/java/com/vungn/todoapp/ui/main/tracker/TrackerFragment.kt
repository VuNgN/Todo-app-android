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
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.databinding.FragmentTrackerBinding
import com.vungn.todoapp.ui.main.tracker.adapter.HorizontalCalendarAdapter
import com.vungn.todoapp.ui.main.tracker.adapter.VerticalTaskAdapter
import com.vungn.todoapp.ui.main.tracker.contract.TrackerViewModel
import com.vungn.todoapp.ui.main.tracker.contract.implement.TrackerViewModelImpl
import com.vungn.todoapp.util.TimeUtil.formatToISO8601
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
        vm.date().observe(viewLifecycleOwner) {
            Log.d("", "handleEvent: $it")
        }
    }

    private fun setupUi() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.calendarRecycleView)
        if (vm.date().value == null) {
            vm.date().postValue(Calendar.getInstance().time)
        }
        setUpCalendar()
        setUpTasks()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpTasks() {
        val taskAdapter = VerticalTaskAdapter()
        binding.taskRecycleView.adapter = taskAdapter
        taskAdapter.setData(listOf())
        vm.date().observe(viewLifecycleOwner) {
            Log.d("", "setUpTasks: change to ${formatToISO8601(it)}")
            taskAdapter.setData(vm.getTasks())
            taskAdapter.notifyDataSetChanged()
        }
        taskAdapter.setOnItemClickListener(object : VerticalTaskAdapter.OnItemClickListener {
            override fun onItemClick(task: Task) {
                val action = TrackerFragmentDirections.actionTrackerFragmentToTaskFragment(task)
                findNavController().navigate(action)
            }
        })
    }

    private fun setUpCalendar() {
        val edge = resources.getDimensionPixelSize(R.dimen.edge_horizontal)
        val itemWidth = (requireContext().resources.displayMetrics.widthPixels - edge * 2) / 7

        val dates = ArrayList<Date>()
        val cal = Calendar.getInstance(Locale.ENGLISH)
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        dates.clear()
        cal.set(Calendar.DAY_OF_MONTH, 1)

        while (dates.size < maxDaysInMonth) {
            dates.add(cal.time)
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }

        binding.apply {
            val adapter =
                HorizontalCalendarAdapter(requireContext(), itemWidth, vm.date().value)
            adapter.setData(dates)
            calendarRecycleView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            calendarRecycleView.adapter = adapter
            val currentPosition = Calendar.getInstance()[Calendar.DAY_OF_MONTH] - 1
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
                    vm.date().postValue(dates[position])
                }
            })
        }
    }
}