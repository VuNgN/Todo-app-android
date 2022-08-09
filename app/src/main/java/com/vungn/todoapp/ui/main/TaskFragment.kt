package com.vungn.todoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vungn.todoapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private var binding: FragmentTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTaskBinding.inflate(inflater, container, false).also {
        binding = it
    }.root
}