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
import androidx.fragment.app.*
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.gson.Gson
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.FragmentAddUserInTaskBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
import com.vungn.todoapp.ui.main.adduser.adapter.AddUserRecycleViewAdapter
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.ui.main.adduser.contract.implement.AddUserInTaskViewModelImpl
import com.vungn.todoapp.ui.main.insert.InsertTaskFragment
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
                setFragmentResult(InsertTaskFragment.KEY_REQUEST,
                    bundleOf(InsertTaskFragment.KEY_REQUEST to viewModel.getListUser()))

                findNavController().navigate(R.id.action_addUserInTaskFragment_to_searchUsserFragment,
                    null)
            }
            confirmButton.setOnClickListener {
                setFragmentResult(InsertTaskFragment.KEY_REQUEST,
                    bundleOf(InsertTaskFragment.KEY_REQUEST to viewModel.getListUser()))

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

        setFragmentResultListener(InsertTaskFragment.KEY_REQUEST) { _, bundle ->
            val list = bundle.getParcelableArrayList<UserResponse>(InsertTaskFragment.KEY_BUNDLE)
                ?: return@setFragmentResultListener
            viewModel.setListUser(list)
            adapter.addList(list)
            binding.recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : AddUserRecycleViewAdapter.OnItemClickListener {
                override fun onDelete(index: Int) {
                    viewModelMainActivity.deleteUser(index)
                }
            })
        }
    }

    companion object {
        private val TAG = "AddUserInTaskFragment"
    }
}