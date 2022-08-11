package com.vungn.todoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vungn.todoapp.R
import com.vungn.todoapp.adapter.HorizontalCalendarAdapter
import com.vungn.todoapp.adapter.VerticalTaskAdapter
import com.vungn.todoapp.databinding.FragmentTrackerBinding
import com.vungn.todoapp.model.FakeData.todayTasks

class TrackerFragment : Fragment() {
    private lateinit var binding: FragmentTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTrackerBinding.inflate(inflater, container, false).also {
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
    }

    private fun setupUi() {
        val dayOfMonth = mutableListOf("6", "7", "8", "9", "10", "11", "12")

        val edge = resources.getDimensionPixelSize(R.dimen.edge_horizontal)
        val itemWidth = (requireContext().resources.displayMetrics.widthPixels - edge * 2) / 7

        binding.apply {
            val adapter = HorizontalCalendarAdapter(requireContext(), itemWidth)
            adapter.setData(dayOfMonth)
            calendarRecycleView
            calendarRecycleView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            calendarRecycleView.adapter = adapter

            val taskAdapter = VerticalTaskAdapter { task ->
                val action = TrackerFragmentDirections.actionTrackerFragmentToTaskFragment(task)
                findNavController().navigate(action)
            }
            taskAdapter.setData(todayTasks)
            taskRecycleView.adapter = taskAdapter
        }
    }
}