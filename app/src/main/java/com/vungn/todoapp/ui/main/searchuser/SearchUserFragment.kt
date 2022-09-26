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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.databinding.FragmentSearchUserBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
import com.vungn.todoapp.ui.main.searchuser.adapter.SearchUserAdapter
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import com.vungn.todoapp.ui.main.searchuser.constract.implement.SearchUserViewModelImpl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchUserFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentSearchUserBinding
    private val viewmodel: SearchUserViewModel by viewModels<SearchUserViewModelImpl>()
    private val viewModelMainActivity: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentSearchUserBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewmodel = viewmodel
        loadRecycleView()
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()
    }

    private fun handleEvent() {
        binding.apply {
            searchUser.requestFocus()
            val imm: InputMethodManager? =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm!!.showSoftInput(binding.searchUser, InputMethodManager.SHOW_IMPLICIT)
            buttonBack.setOnClickListener {
                findNavController().popBackStack(R.id.addUserInTaskFragment,
                    false)
            }

            searchUser.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(charSequence: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewmodel!!.search()
                }
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
                R.drawable.devider
            ))!!
        )
        binding.recycleView.addItemDecoration(itemDecorator)

        viewmodel.listUserSearch.observe(viewLifecycleOwner) {
            val adapter = SearchUserAdapter(it)
            binding.recycleView.adapter = adapter

            adapter.setOnItemClickListener(object : SearchUserAdapter.OnItemClickSearchListener {
                override fun onItemClick(user: UserResponse) {
                    viewModelMainActivity.addUserInLiveData(user)
                    Log.d(TAG, "onItemClick: ${user.name}")
                    findNavController().popBackStack()
                }
            })
        }
    }
    companion object{
        private val TAG = "SearchUserFragment"
    }
}
