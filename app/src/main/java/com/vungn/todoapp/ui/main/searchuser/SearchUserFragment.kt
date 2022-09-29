package com.vungn.todoapp.ui.main.searchuser

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.FragmentSearchUsserBinding
import com.vungn.todoapp.ui.main.insert.InsertTaskFragment
import com.vungn.todoapp.ui.main.searchuser.adapter.SearchUserAdapter
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import com.vungn.todoapp.ui.main.searchuser.constract.implement.SearchUserViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : Fragment() {

    private lateinit var binding: FragmentSearchUsserBinding

    private val vm: SearchUserViewModel by viewModels<SearchUserViewModelImpl>()

    private val adapter = SearchUserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentSearchUsserBinding.inflate(inflater, container, false).also {
        // Inflate the layout for this fragment
        binding = it
        binding.viewmodel = vm
        loadRecycleView()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()
    }

    private fun handleEvent() {
        binding.apply {
            searchUser.requestFocus()
            val imm: InputMethodManager =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.searchUser, InputMethodManager.SHOW_IMPLICIT)
            buttonBack.setOnClickListener {
                findNavController().popBackStack(R.id.addUserInTaskFragment,
                    false)
            }

            searchUser.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(charSequence: Editable?) {
                    try {
                        viewmodel!!.search()
                    } catch (e: Exception) {
                        Log.d(TAG, "afterTextChanged: exception = $e")
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
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

        val list = mutableListOf<UserResponse>()

        vm.listUserSearch.observe(viewLifecycleOwner) {

            setFragmentResultListener(InsertTaskFragment.KEY_REQUEST) { _, bundle ->
                val listRequest =
                    bundle.getParcelableArrayList<UserResponse>(InsertTaskFragment.KEY_BUNDLE)
                        ?: return@setFragmentResultListener
                list.addAll(listRequest)
            }

            if (list.size == 0) {
                adapter.addList(it)
            } else {
                adapter.addList(it, list)
            }
            binding.recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : SearchUserAdapter.OnItemClickListener {
                override fun onItemClick(user: UserResponse) {
                    list.add(user)
                    setFragmentResult(InsertTaskFragment.KEY_REQUEST,
                        bundleOf(InsertTaskFragment.KEY_BUNDLE to list))
                    Log.d(TAG, "onItemClick: ${user.name}")
                    findNavController().popBackStack()
                }
            })
        }
    }

    companion object {
        private val TAG = "SearchUsserFragment"
    }
}