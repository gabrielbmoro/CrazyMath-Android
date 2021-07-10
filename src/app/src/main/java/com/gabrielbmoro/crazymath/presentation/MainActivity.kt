package com.gabrielbmoro.crazymath.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationController
import org.koin.android.ext.android.inject

open class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: NavigationController by inject()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.activity_main_nav_host_fragment)

        navController.let {
            navigator.listening(this, it)
        }
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }

}