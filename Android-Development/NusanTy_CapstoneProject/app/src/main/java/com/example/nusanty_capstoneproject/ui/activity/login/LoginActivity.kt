package com.example.nusanty_capstoneproject.ui.activity.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nusanty_capstoneproject.data.model.UserSession
import com.example.nusanty_capstoneproject.databinding.ActivityLoginBinding
import com.example.nusanty_capstoneproject.helper.UserPreference
import com.example.nusanty_capstoneproject.ui.activity.main.MainActivity
import com.example.nusanty_capstoneproject.ui.activity.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var pref: SharedPreferences
    private lateinit var userPref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupPreference()
        setupViewModel()
        setupAction()
    }

    private fun setupPreference() {
        pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        userPref = UserPreference(this)
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.isLoading.observe(this) { showLoading(it) }
        loginViewModel.toastMessage.observe(this) { showToast(it) }
    }

    private fun setupAction(){
        binding.btnLogin.setOnClickListener {
            login()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(
                intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity)
                    .toBundle()
            )
        }
    }

    private fun login() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        when {
            email.isEmpty() -> {
                binding.layoutEmail.error = "Please enter your email"
            }
            password.isEmpty() -> {
                binding.layoutPassword.error = "Please enter your password"
            }
            else -> {
                loginViewModel.loginUser(email, password)
                loginViewModel.userLogin.observe(this) {
                    binding.progressBar.visibility = View.VISIBLE
                    if (it != null) {
                        AlertDialog.Builder(this).apply {
                            setTitle("Login Success!")
                            setMessage("Welcome, ${it.name}!")
                            setPositiveButton("Ok") { _, _ ->
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(
                                    intent,
                                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity)
                                        .toBundle()
                                )
                                finish()
                            }
                            create()
                            show()
                        }
                        saveSession(UserSession(it.name, it.token, it.userId, true))
                    }

                }
            }
        }
    }

    private fun saveSession(user: UserSession) {
        userPref.setUser(user)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val SHARED_PREFERENCES = "shared_preferences"
    }
}