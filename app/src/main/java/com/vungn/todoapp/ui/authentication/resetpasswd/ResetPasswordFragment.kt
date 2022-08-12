package com.vungn.todoapp.ui.authentication.resetpasswd

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    private var emailPattern: Regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentResetPasswordBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEvent()
    }

    private fun handleEvent() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.MailEditText.addTextChangedListener(checkValidation())
    }

    private fun checkValidation() =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s != null) {
                    if (s.toString().matches(emailPattern) && s.isNotEmpty()) {
                        binding.SendButton.isEnabled = true
                        binding.SendButton.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.enter_arrow_icon,
                            0
                        )
                    } else {
                        binding.SendButton.isEnabled = false
                        binding.SendButton.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.enter_arrow_disable_icon,
                            0
                        )
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
}