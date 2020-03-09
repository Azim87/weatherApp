package com.example.wheatherapp.ui.main

import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R
import com.example.wheatherapp.service.NetworkService
import com.example.wheatherapp.ui.map.MapFragment
import com.example.wheatherapp.ui.search.SearchFragment
import com.example.wheatherapp.ui.user.UserFragment
import com.example.wheatherapp.utils.NetworkChangeReceiver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBroadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationView()
        createFragment(MapFragment.newInstance())
        mBroadcastReceiver = NetworkChangeReceiver()
        startService(Intent(this@MainActivity, NetworkService::class.java))
    }

    private fun setUpBottomNavigationView() {
        main_bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_globe -> createFragment(MapFragment.newInstance())
                R.id.navigation_map -> createFragment(SearchFragment.newInstance())
                R.id.navigation_user -> createFragment(UserFragment.newInstance())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun createFragment(fragment: Fragment) {
        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.replace(R.id.main_container, fragment)
        transactionManager.commit()
    }

    override fun onBackPressed() {
        MapFragment().onBackPress()
        super.onBackPressed()
    }
}
