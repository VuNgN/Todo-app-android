package com.vungn.todoapp.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.tabs.TabLayout
import com.vungn.todoapp.R
import com.vungn.todoapp.ui.main.home.adapter.HorizontalTaskAdapter
import com.vungn.todoapp.databinding.FragmentHomeBinding
import com.vungn.todoapp.data.model.FakeData.todayTasks
import com.vungn.todoapp.data.model.FakeData.tomorrowTasks
import com.vungn.todoapp.data.model.FakeData.upcomingTasks
import com.vungn.todoapp.data.model.Task

class HomeFragment : Fragment() {
    private lateinit var adapter: HorizontalTaskAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        handleEvent()
    }

    private fun handleEvent() {
        binding.apply {
            avatarButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment, null)
            }
            trackerButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_trackerFragment, null)
            }
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            updateRecycleView(todayTasks)
                        }
                        1 -> {
                            updateRecycleView(tomorrowTasks)
                        }
                        2 -> {
                            updateRecycleView(upcomingTasks)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    private fun setupUi() {
        adapter = HorizontalTaskAdapter()
        binding.apply {
            recycleView.adapter = adapter
            recycleView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            val itemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.decorator
                )!!
            )
            recycleView.addItemDecoration(itemDecoration)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.recycleView)
            updateRecycleView(todayTasks)
        }
    }

    private fun updateRecycleView(data: MutableList<Task>) {
        adapter.setData(data)
        adapter.notifyChange(
            maxOf(
                0,
                (binding.recycleView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() - 3
            ),
            minOf(
                (binding.recycleView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 5,
                adapter.itemCount
            )
        )
        (binding.recycleView.layoutManager as LinearLayoutManager).scrollToPosition(0)
        binding.recycleView.startLayoutAnimation()
    }
}