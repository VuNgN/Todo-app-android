package com.vungn.todoapp.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private var binding: FragmentTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTaskBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.task = arguments?.getParcelable("task")
        binding?.buttonBack?.setOnClickListener {
            back()
        }
    }

    private fun back() {
        findNavController().popBackStack()
    }
}