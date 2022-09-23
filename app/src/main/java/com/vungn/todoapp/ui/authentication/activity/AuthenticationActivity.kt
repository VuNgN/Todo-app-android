package com.vungn.todoapp.ui.authentication.activity

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.ActivityAuthenticationBinding
import com.vungn.todoapp.ui.authentication.activity.contract.AuthenticationViewModel
import com.vungn.todoapp.ui.authentication.activity.contract.implement.AuthenticationViewModelImpl
import com.vungn.todoapp.ui.main.activity.MainActivity
import com.vungn.todoapp.util.extention.CommonEx.popupAndGo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private val viewHolder: AuthenticationViewModel by viewModels<AuthenticationViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (viewHolder.isLoggedIn()) {
            login()
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewHolder.isFirstRun()) {
            binding.myNavHostFragment.popupAndGo(R.id.loginFragment, R.id.getStartedFragment)
        } else {
            binding.myNavHostFragment.popupAndGo(R.id.getStartedFragment, R.id.loginFragment)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun login() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}