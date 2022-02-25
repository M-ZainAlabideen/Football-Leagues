package com.football.league.views.activities

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.football.league.R
import com.football.league.classes.GlobalFunctions
import com.football.league.classes.Navigator
import com.football.league.databinding.ActivityMainBinding
import com.football.league.views.fragments.FavoriteFragment
import com.football.league.views.fragments.LeaguesFragment

class MainActivity : AppCompatActivity() {
    companion object {
        var binding: ActivityMainBinding? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)
        GlobalFunctions.setUpFont()
        setupBottomNavigationView()
    }


    private fun setupBottomNavigationView() {
        val menu: Menu = binding!!.bottomAppBar.menu
        selectFragment(menu.getItem(0))
        binding!!.bottomAppBar.setOnItemSelectedListener { item ->
            selectFragment(item)
            false
        }
    }

    private fun selectFragment(item: MenuItem) {
        when (item.itemId) {
            R.id.leagues -> {
                Navigator.loadFragment(
                    this,
                    LeaguesFragment.newInstance(this),
                    R.id.fragment_container,
                    true
                )
            }

            R.id.favorite -> {
                Navigator.loadFragment(
                    this,
                    FavoriteFragment.newInstance(this),
                    R.id.fragment_container,
                    true
                )
            }

        }
    }

}