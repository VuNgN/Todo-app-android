package com.vungn.todoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vungn.todoapp.adapter.MainTaskRecycleViewAdapter
import com.vungn.todoapp.databinding.FragmentHomeBinding
import com.vungn.todoapp.model.Task
import com.vungn.todoapp.model.User

class HomeFragment : Fragment() {
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
    }

    private fun setupUi() {
        val tasks = mutableListOf(
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

        val adapter = MainTaskRecycleViewAdapter()
        adapter.addData(tasks)
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }
}