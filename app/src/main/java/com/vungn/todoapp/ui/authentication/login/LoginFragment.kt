package com.vungn.todoapp.ui.authentication.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
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
import com.vungn.todoapp.ui.main.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment : Fragment(), LifecycleOwner {
    private lateinit var signUpButton: TextView
    private lateinit var signInButton: Button
    private lateinit var forgotPassword: TextView
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var _binding: FragmentLoginBinding? = null
    private lateinit var liveDataToken: MutableLiveData<String>

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
        _binding?.viewModel = viewModel
        //
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configGoogle()
        bindingView()
        handleEvents()
        viewModel.checkLogin.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(activity, MainActivity::class.java))
                Toast.makeText(requireActivity(), "Login Successfully!!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireActivity(), "Login fail", Toast.LENGTH_SHORT)
            }
        }
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

                    Log.d(TAG, "token: $idToken, ${account.displayName}, ${account.email}")
                    viewModel.loginWithGoogle(idToken.toString())
                } catch (e: ApiException) {
                    Log.d(TAG, "handleSignInResult:error: $e")
                }
            }
        }

    private fun bindingView() {
        signInButton = binding.SignInButton
        signUpButton = binding.SignUpButton
        forgotPassword = binding.ForgotPasswordTextView
    }

    companion object {
        private val TAG = "LoginFragment"
    }
}