package com.vungn.todoapp.ui.authentication.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentLoginBinding
import com.vungn.todoapp.ui.authentication.login.contract.LoginViewModel
import com.vungn.todoapp.ui.authentication.login.contract.implement.LoginViewModelImpl


class LoginFragment : Fragment() {
    private lateinit var signUpButton: TextView
    private lateinit var signInButton: Button
    private lateinit var forgotPassword: TextView
    private lateinit var viewModel: LoginViewModel
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // đang khởi tạo dữ liệu, sau này sẽ sử dụng dependency để khởi tạo ở nơi khác
        val factory =
            LoginViewModelImpl.Factory(this@LoginFragment.requireActivity().application) // Factory
        viewModel =
            ViewModelProvider(this, factory)[LoginViewModelImpl::class.java] // ViewModel
        _binding?.viewModel = viewModel
        //
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configGoogle()
        bindingView()
        handleEvents()
    }

    private fun configGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun handleEvents() {
        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null)
        }
        forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment, null)
        }
        viewModel.navigation().observe(viewLifecycleOwner) {
            findNavController().navigate(it.id, it.data)
        }
        binding.ggBtn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            resultLaunch.launch(signInIntent)
        }
    }

    private var resultLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java) as GoogleSignInAccount
                    val idToken = account.idToken

                    // TODO(developer): send ID Token to server and validate

                    Log.d("", "token: $idToken, ${account.displayName}, ${account.email}")
                } catch (e: ApiException) {
                    Log.d("", "handleSignInResult:error: $e")
                }
            }
        }

    private fun bindingView() {
        signInButton = binding.SignInButton
        signUpButton = binding.SignUpButton
        forgotPassword = binding.ForgotPasswordTextView
    }
}