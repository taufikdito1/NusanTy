package com.example.nusanty_capstoneproject.ui.activity.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.databinding.ActivityMainBinding
import com.example.nusanty_capstoneproject.helper.UserPreference
import com.example.nusanty_capstoneproject.ui.activity.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userPref: UserPreference
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        userPref = UserPreference(this)

        setupView()
        validate()

    }
    override fun onBackPressed() {

        Toast.makeText(applicationContext,"Tekan sekali lagi untuk keluar",Toast.LENGTH_SHORT).show()
        count += 1
        if (count == 2) {
            finish()
        }
    }

    private fun setupView(){
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_akun
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun validate() {
        if (!userPref.getUser().isLogin) {
            val login = userPref.getUser().isLogin
            Log.d("tag", login.toString())
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
            )
            finish()
        }
    }

}