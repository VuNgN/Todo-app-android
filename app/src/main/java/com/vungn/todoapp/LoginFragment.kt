package com.vungn.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var signUpButton: TextView
    private lateinit var emailEditText: EditText
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingView()
        handleEvents()
    }

    private fun handleEvents() {
        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null)
        }
    }

    private fun bindingView() {
        signUpButton = binding.SignUpButton
        emailEditText = binding.MailEditText
    }
}