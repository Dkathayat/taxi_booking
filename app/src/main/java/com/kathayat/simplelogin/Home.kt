package com.kathayat.simplelogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.kathayat.simplelogin.Extensions.toast
import com.kathayat.simplelogin.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val personFragment = PersonFragment()
        val accountFragment = AccountFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.bhome -> setCurrentFragment(homeFragment)
                R.id.bperson -> setCurrentFragment(personFragment)
                R.id.baccount -> setCurrentFragment(accountFragment)
            }
            true
        }

//        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//
//        override fun onQueryTextSubmit(query: String?): Boolean {
//            TODO("Not yet implemented")
//        }
//
//        override fun onQueryTextChange(newText: String?): Boolean {
//            TODO("Not yet implemented")
//        }
//
//
//    })
    }

    private fun setCurrentFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutBottomNav, fragment)
            commit()
        }

    }
}

