package com.vungn.todoapp.ui.main.searchuser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentSearchUserBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
import com.vungn.todoapp.ui.main.searchuser.adapter.SearchAutoCompletedTVAdapter
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

        binding.searchUser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewmodel.search()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        viewmodel.listUserSearch.observe(viewLifecycleOwner) {
             val list: MutableList<String> = mutableListOf()
            for(user in it){
                list.add(user.name)
            }
            val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,list)
//            val adapter = SearchAutoCompletedTVAdapter(requireContext(), it)
            binding.searchUser.setAdapter(adapter)
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

    }
}
