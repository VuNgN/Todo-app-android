package com.vungn.todoapp.ui.main.adduser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
class AddUserInTaskFragment : Fragment(),LifecycleOwner {

    private val viewModel: AddUserInTaskViewModel by viewModels<AddUserInTaskViewModelImpl>()
    private lateinit var binding: FragmentAddUserInTaskBinding
    private val viewModelMainActivity: MainViewModel by viewModels<MainViewModelImpl>()

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
        loadRecycleView()
        handleEvent()

    }

    private fun handleEvent() {
        binding.searchUser.setOnClickListener {
            findNavController().navigate(R.id.action_addUserInTaskFragment_to_searchUserFragment,
                null)
        }

    }

    private fun loadRecycleView() {
        val itemDecorator =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
            (ContextCompat.getDrawable(
                requireContext(),
                R.drawable.devider
            ))!!
        )
        binding.recycleView.addItemDecoration(itemDecorator)

        viewModelMainActivity.liveDataUserGuest.observe(viewLifecycleOwner) {
            Log.d(TAG, "loadRecycleView: ${it.size}")
            val adapter = AddUserRecycleViewAdapter(it)
            binding.recycleView.adapter = adapter
        }
    }

    companion object{
        private val TAG = "AddUserInTaskFragment"
    }
}