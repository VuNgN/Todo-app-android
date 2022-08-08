package com.vungn.todoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.vungn.todoapp.R
import com.vungn.todoapp.adapter.MainTaskRecycleViewAdapter
import com.vungn.todoapp.databinding.FragmentHomeBinding
import com.vungn.todoapp.model.Task
import com.vungn.todoapp.model.User

class HomeFragment : Fragment() {
    private lateinit var adapter: MainTaskRecycleViewAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val todayTasks = mutableListOf(
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                )
            )
        ),
        Task(
            "Training",
            "10:00 PM",
            "Kotlin class",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                )
            )
        ),
        Task(
            "aaaaaaaaaazzzzazazazazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "12:00 AM",
            "lolololololololololololololololololololololololololololoololololololololololo",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-vui-nhon.jpg"
                ),
                User(
                    "",
                    "",
                    "https://img5.thuthuatphanmem.vn/uploads/2021/11/22/anh-avatar-gau-cute_092859783.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf()
        )
    )
    private val tomorrowTasks = mutableListOf(
        Task(
            "Training",
            "10:00 PM",
            "Kotlin class",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-vui-nhon.jpg"
                ),
                User(
                    "",
                    "",
                    "https://img5.thuthuatphanmem.vn/uploads/2021/11/22/anh-avatar-gau-cute_092859783.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf()
        ),
        Task(
            "aaaaaaaaaazzzzazazazazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "12:00 AM",
            "lolololololololololololololololololololololololololololoololololololololololo",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                )
            )
        )
    )
    private val upcomingTasks = mutableListOf(
        Task(
            "aaaaaaaaaazzzzazazazazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "12:00 AM",
            "lolololololololololololololololololololololololololololoololololololololololo",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                )
            )
        ),
        Task(
            "Training",
            "10:00 PM",
            "Kotlin class",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-vui-nhon.jpg"
                ),
                User(
                    "",
                    "",
                    "https://img5.thuthuatphanmem.vn/uploads/2021/11/22/anh-avatar-gau-cute_092859783.jpg"
                )
            )
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf()
        ),
        Task(
            "Meeting",
            "9:00 AM",
            "Discuss team tasks for the day",
            mutableListOf(
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-meo-con-than-chet-700x695.jpg"
                ),
                User(
                    "",
                    "",
                    "https://kynguyenlamdep.com/wp-content/uploads/2022/06/avatar-cute-cho-co-nang-nghien-tra-sua.jpg"
                ),
                User(
                    "",
                    "",
                    "https://linhkiem.vn/avatar-de-thuong-cho-facebook/imager_70231.jpg"
                )
            )
        )
    )

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
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun setupUi() {
        adapter = MainTaskRecycleViewAdapter(requireContext())
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        val itemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.decorator
            )!!
        )
        binding.recycleView.addItemDecoration(itemDecoration)
        updateRecycleView(todayTasks)
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