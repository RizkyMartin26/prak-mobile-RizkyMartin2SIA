package com.example.raseoapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.raseoapps.databinding.ActivityBaseBinding
import com.example.raseoapps.home.HomeFragment
import com.example.raseoapps.message.MessageFragment
import com.example.raseoapps.more.MoreFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.message -> replaceFragment(MessageFragment())
                R.id.more -> replaceFragment(MoreFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
