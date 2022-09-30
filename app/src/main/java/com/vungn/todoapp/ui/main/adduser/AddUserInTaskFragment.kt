package com.vungn.todoapp.ui.main.adduser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserRespons
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.FragmentAddUserInTaskBinding
import com.vungn.todoapp.ui.main.adduser.adapter.AddUserRecycleViewAdapter
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.ui.main.adduser.contract.implement.AddUserInTaskViewModelImpl
import com.vungn.todoapp.ui.main.searchuser.SearchUserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserInTaskFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentAddUserInTaskBinding

    private val viewModel: AddUserInTaskViewModel by viewModels<AddUserInTaskViewModelImpl>()

    private val adapter = AddUserRecycleViewAdapter()

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

        val users: UserRespons = AddUserInTaskFragmentArgs.fromBundle(requireArguments()).users
//        if (users.size>0) {
        viewModel.setListUser(users)
//        }

        setFragmentResultListener(SearchUserFragment.KEY_SEARCH_REQUEST) { _, bundle ->
            val user = bundle.getParcelable<UserResponse>(SearchUserFragment.KEY_USER_SEARCH)
                ?: return@setFragmentResultListener
            viewModel.addUser(user)
        }

        handleEvent()
        loadRecycleView()
    }

    private fun handleEvent() {
        binding.apply {
            searchUser.setOnClickListener {
                val users = UserRespons()

                users.addAll(viewModel.users.value!!)
                val action =
                    AddUserInTaskFragmentDirections.actionAddUserInTaskFragmentToSearchUsserFragment(
                        users)
                findNavController().navigate(action)
            }
            confirmButton.setOnClickListener {
                setFragmentResult(KEY_REQUEST, bundleOf(KEY_NEW_LIST to viewModel.users.value))

                findNavController().popBackStack()
            }
            buttonBack.setOnClickListener {
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

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.addList(it)
            binding.recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : AddUserRecycleViewAdapter.OnItemClickListener {
                override fun onDelete(index: Int) {
                    viewModel.deleteUser(index)
                }
            })
        }

    }

    companion object {
        private val TAG = "AddUserInTaskFragment"
        const val KEY_REQUEST = "REQUEST_LIST_USER"
        const val KEY_NEW_LIST = "BUNDLE_NEW_LIST"
    }
}