package com.vungn.todoapp.ui.main.adduser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentAddUserInTaskBinding
import com.vungn.todoapp.databinding.FragmentSearchUserBinding
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.ui.main.adduser.contract.implement.AddUserInTaskViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserInTaskFragment : Fragment() {

    private val viewModel: AddUserInTaskViewModel by viewModels<AddUserInTaskViewModelImpl>()
    private lateinit var binding: FragmentAddUserInTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentAddUserInTaskBinding.inflate(inflater, container, false).also {
        // Inflate the layout for this fragment
        binding = it
        binding.viewmodel = viewModel

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()

    }

    private fun handleEvent() {
        binding.searchUser.setOnClickListener {
            findNavController().navigate(R.id.action_addUserInTaskFragment_to_searchUserFragment,
                null)
        }
    }

}