package com.vungn.todoapp.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.tabs.TabLayout
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.databinding.FragmentHomeBinding
import com.vungn.todoapp.ui.main.home.adapter.HorizontalTaskAdapter
import com.vungn.todoapp.ui.main.home.constant.TabType
import com.vungn.todoapp.ui.main.home.contract.HomeViewModel
import com.vungn.todoapp.ui.main.home.contract.implement.HomeViewModelImpl

class HomeFragment : Fragment() {
    private lateinit var adapter: HorizontalTaskAdapter
    private lateinit var vm: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        val factory = HomeViewModelImpl.Factory(this@HomeFragment.requireActivity().application)
        vm = ViewModelProvider(this, factory)[HomeViewModelImpl::class.java]
        _binding = it
        _binding?.vm = vm
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        handleEvent()
    }

    @SuppressLint("NotifyDataSetChanged")
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
                            vm?.onTabChanges(TabType.TODAY)
                        }
                        1 -> {
                            vm?.onTabChanges(TabType.TOMORROW)
                        }
                        2 -> {
                            vm?.onTabChanges(TabType.UPCOMING)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
        vm.tasks().observe(viewLifecycleOwner) {
            updateRecycleView(it)
            adapter.notifyDataSetChanged()
        }
        vm.getTodayTask()
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
            updateRecycleView(listOf())
        }
    }

    private fun updateRecycleView(data: List<Task>) {
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