package com.vungn.todoapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.vungn.todoapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var signInTextView: TextView
    private lateinit var emailEditText: EditText
    private var _binding: FragmentRegisterBinding? = null
    private var emailPattern: Regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingView()
        handleEvents()
    }

    private fun handleEvents() {
        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        signInTextView.setOnClickListener {
            findNavController().popBackStack()
        }
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().matches(emailPattern) && s.isNotEmpty()) {
                    binding.MailTextInputLayout.isErrorEnabled = false
                    binding.MailTextInputLayout.error = null
                } else {
                    binding.MailTextInputLayout.isErrorEnabled = true
                    binding.MailTextInputLayout.error = "Invalid email format"
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // other stuffs
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // other stuffs
            }
        })
    }

    private fun bindingView() {
        topAppBar = binding.topAppBar
        signInTextView = binding.SignInButton
        emailEditText = binding.MailEditText
    }
}