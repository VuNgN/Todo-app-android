package com.vungn.todoapp.ui.authentication.register

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
import com.google.android.material.textfield.TextInputLayout
import com.vungn.todoapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var signInTextView: TextView
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private var emailPattern: Regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    private var passwordPattern: Regex =
        Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_\\-.,|~/+=])(?=\\S+$).{4,}$")
    private var _binding: FragmentRegisterBinding? = null

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
        emailEditText.addTextChangedListener(
            checkValidation(
                binding.MailTextInputLayout,
                emailPattern,
                "Invalid email format"
            )
        )
        passwordEditText.addTextChangedListener(
            checkValidation(
                binding.PasswordTextInputLayout,
                passwordPattern,
                """
                    * Must be at least 6 characters.
                    * Must have at least 1 upper case letter (A-Z)
                    * Must have at least 1 number (0-9)
                    * Must have at least 1 special character
                """.trimIndent()
            )
        )
        confirmPasswordEditText.addTextChangedListener(
            checkSamePassword(
                binding.ConfirmPasswordTextInputLayout
            )
        )
    }

    private fun bindingView() {
        topAppBar = binding.topAppBar
        signInTextView = binding.SignInButton
        emailEditText = binding.MailEditText
        passwordEditText = binding.PasswordEditText
        confirmPasswordEditText = binding.ConfirmPasswordEditText
    }

    private fun checkValidation(
        layout: TextInputLayout,
        pattern: Regex,
        message: String
    ): TextWatcher =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s != null) {
                    if (s.toString().matches(pattern) && s.isNotEmpty()) {
                        layout.isErrorEnabled = false
                        layout.error = null
                    } else {
                        layout.isErrorEnabled = true
                        layout.error = message
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }
        }

    private fun checkSamePassword(
        layout: TextInputLayout
    ): TextWatcher =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.toString() == binding.PasswordEditText.text.toString() && s.isNotEmpty()) {
                        layout.isErrorEnabled = false
                        layout.error = null
                    } else {
                        layout.isErrorEnabled = true
                        layout.error = "Password do not match"
                    }
                }
            }
        }
}