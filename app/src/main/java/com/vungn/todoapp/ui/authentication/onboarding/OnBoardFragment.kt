package com.vungn.todoapp.ui.authentication.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.vungn.todoapp.R
import com.vungn.todoapp.ui.authentication.onboarding.adapter.OnBoardViewPagerAdapter
import com.vungn.todoapp.databinding.FragmentOnBoardBinding
import com.vungn.todoapp.util.extention.WindowEx.showStatusBar

class OnBoardFragment : Fragment() {
    private lateinit var skipButton: TextView
    private lateinit var nextButton: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: OnBoardViewPagerAdapter
    private lateinit var wormDotsIndicator: WormDotsIndicator
    private var _binding: FragmentOnBoardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().showStatusBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentOnBoardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingView()
        handleEvent()
        configViewPager()
    }

    private fun handleEvent() {
        skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment, null)
        }
        nextButton.setOnClickListener {
            if (viewPager.currentItem == OnBoardViewPagerAdapter.positions.size - 1) {
                findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment, null)
            } else {
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }
        }
    }

    private fun configViewPager() {
        viewPagerAdapter = OnBoardViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        wormDotsIndicator.attachTo(viewPager)
    }

    private fun bindingView() {
        skipButton = binding.SkipButton
        nextButton = binding.NextButton
        viewPager = binding.viewPager
        wormDotsIndicator = binding.wormDotsIndicator
    }
}
