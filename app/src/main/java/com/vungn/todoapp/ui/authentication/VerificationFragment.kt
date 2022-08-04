package com.vungn.todoapp.ui.authentication

import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentVerificationBinding
import java.util.concurrent.TimeUnit

class VerificationFragment : Fragment() {
    private lateinit var countDownTimer: CountDownTimer
    private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentVerificationBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        startCountDown()
        handleEvent()
    }

    private fun handleEvent() {
        binding.CountDown.setOnClickListener {
            countDownTimer.start()
        }
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.ContinueButton.setOnClickListener {
            (requireActivity() as AuthenticationActivity).login()
        }
    }

    private fun setupUi() {
        binding.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                binding.ContinueButton.isEnabled = false
                binding.ContinueButton.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.enter_arrow_disable_icon,
                    0
                )
            }

            override fun onOTPComplete(otp: String) {
                binding.ContinueButton.isEnabled = true
                binding.ContinueButton.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.enter_arrow_icon,
                    0
                )
            }
        }

    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(21000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondUntilFinished = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                if (secondUntilFinished < 10) {
                    binding.CountDown.text =
                        activity?.getString(R.string.count_down_one, secondUntilFinished.toString())
                } else {
                    binding.CountDown.text =
                        activity?.getString(R.string.count_down_two, secondUntilFinished.toString())
                }
                binding.CountDown.isEnabled = false
            }

            override fun onFinish() {
                binding.CountDown.text = activity?.getString(R.string.re_send)
                binding.CountDown.isEnabled = true
            }
        }
        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.onFinish()
    }
}