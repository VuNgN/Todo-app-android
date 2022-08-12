package com.vungn.todoapp.ui.main.privacypolicies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentPrivacyPoliciesBinding

class PrivacyPoliciesFragment : Fragment() {
    private lateinit var binding: FragmentPrivacyPoliciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPrivacyPoliciesBinding.inflate(inflater, container, false).also {
        binding = it
    }.root
}