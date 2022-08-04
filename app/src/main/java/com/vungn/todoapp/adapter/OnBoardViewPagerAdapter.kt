package com.vungn.todoapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vungn.todoapp.ui.authentication.onboarding.OnBoardOneFragment
import com.vungn.todoapp.ui.authentication.onboarding.OnBoardThreeFragment
import com.vungn.todoapp.ui.authentication.onboarding.OnBoardTwoFragment

class OnBoardViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return positions.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            positions[0] -> OnBoardOneFragment()
            positions[1] -> OnBoardTwoFragment()
            positions[2] -> OnBoardThreeFragment()
            else -> OnBoardOneFragment()
        }
    }

    companion object {
        val positions: Array<Int> = arrayOf(0, 1, 2)
    }
}