package com.vungn.todoapp.ui.main.searchuser

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.vungn.todoapp.databinding.FragmentSearchUserBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
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
        binding.searchUser.requestFocus()
        val imm: InputMethodManager? =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm!!.showSoftInput(binding.searchUser, InputMethodManager.SHOW_IMPLICIT)
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack(R.id.addUserInTaskFragment,
                false)
        }

        binding.searchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(charSequence: Editable?) {
                TODO("Not yet implemented")
                viewmodel.search()
            }

        })
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

        viewmodel.listUserSearch.observe(viewLifecycleOwner) {

        }

    }
}
