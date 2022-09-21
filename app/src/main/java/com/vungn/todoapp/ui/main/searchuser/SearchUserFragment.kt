package com.vungn.todoapp.ui.main.searchuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentSearchUserBinding
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import com.vungn.todoapp.ui.main.searchuser.constract.implement.SearchUserViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : Fragment(), LifecycleOwner {

    private var binding: FragmentSearchUserBinding?= null
    private val viewmodel: SearchUserViewModel by viewModels<SearchUserViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentSearchUserBinding.inflate(inflater, container, false).also {
        binding = it
        loadRecycleView()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        binding!!.recycleView.addItemDecoration(itemDecorator)


    }
}
