package com.vungn.todoapp.ui.main.adduser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentAddUserInTaskBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
import com.vungn.todoapp.ui.main.adduser.adapter.AddUserRecycleViewAdapter
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.ui.main.adduser.contract.implement.AddUserInTaskViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserInTaskFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentAddUserInTaskBinding

    private val viewModel: AddUserInTaskViewModel by viewModels<AddUserInTaskViewModelImpl>()
    private val viewModelMainActivity: MainViewModel by activityViewModels<MainViewModelImpl>()

    private val adapter = AddUserRecycleViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentAddUserInTaskBinding.inflate(inflater, container, false).also {
        // Inflate the layout for this fragment
        binding = it
        binding.viewmodel = viewModel
        loadRecycleView()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()

    }

    private fun handleEvent() {
        binding.apply {
            searchUser.setOnClickListener {
                findNavController().navigate(R.id.action_addUserInTaskFragment_to_searchUsserFragment,
                    null)
            }
            confirmButton.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonBack.setOnClickListener {
                viewModelMainActivity.cancelAddUser()
                findNavController().popBackStack()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadRecycleView() {
        val itemDecorator =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
            (ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider
            ))!!
        )
        binding.recyclerView.addItemDecoration(itemDecorator)

        viewModelMainActivity.guests.observe(viewLifecycleOwner) {
            adapter.addList(it)
            binding.recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : AddUserRecycleViewAdapter.OnItemClickListener {
                override fun onDelete(index: Int) {
                    viewModelMainActivity.deleteUser(index)
                }
            })

            Log.d(TAG, "loadRecycleView: list change ${it.size}")
        }
    }

    companion object {
        private val TAG = "AddUserInTaskFragment"
    }
}