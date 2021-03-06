package com.cs492.cocktailapp.ui.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.cs492.cocktailapp.R
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up the view pager
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_browse)
        val browsePagerAdapter = BrowseFragmentAdapter(this, BrowseFragmentAdapter.CATEGORIES.size)
        viewPager.adapter = browsePagerAdapter

        // Connect the tabLayout to the view pager
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            val resource = BrowseFragmentAdapter.CATEGORIES[position].stringResource
            tab.text = getString(resource)
        }.attach()

        title = getString(R.string.main_activity_title)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // TODO : Launch search intent
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}