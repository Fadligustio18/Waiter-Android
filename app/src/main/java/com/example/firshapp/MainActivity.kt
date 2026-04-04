package com.example.firshapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Cara yang lebih aman untuk mendapatkan NavController di MainActivity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomnav.setupWithNavController(navController)
    }
}
