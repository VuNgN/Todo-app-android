package com.vungn.todoapp.ui.main.setting

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentSettingBinding
import com.vungn.todoapp.model.FakeData.loggedInUser


class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSettingBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            user = loggedInUser
        }
        handleEvent()
        setupStatusBarColor(R.color.primary_variant)
    }

    private fun handleEvent() {
        binding.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            aboutButton.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_aboutFragment, null)
            }
            privacyButton.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_privacyPoliciesFragment)
            }
        }
    }

    private fun setupStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            requireActivity().window.statusBarColor = resources.getColor(color, null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setupStatusBarColor(R.color.white)
    }

    override fun onDetach() {
        super.onDetach()
        setupStatusBarColor(R.color.white)
    }

}