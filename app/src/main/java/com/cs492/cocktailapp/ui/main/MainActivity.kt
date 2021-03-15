package com.cs492.cocktailapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.cs492.cocktailapp.R
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.data.Tab
import com.cs492.cocktailapp.ui.cocktail.DetailedCocktailActivity
import com.cs492.cocktailapp.ui.search.SearchCocktailActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), CocktailFragmentListener {

    private lateinit var viewPager: ViewPager2

    companion object {
        val CATEGORIES = arrayOf(
                Tab.Popular,
                Tab.New,
                Tab.Random,
                Tab.Saved
        )
        val SAVED_PAGE_INDEX = CATEGORIES.indexOf(Tab.Saved)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up the view pager
        viewPager = findViewById<ViewPager2>(R.id.view_pager_browse)
        val browsePagerAdapter = FragmentPageAdapter(this, CATEGORIES)
        viewPager.adapter = browsePagerAdapter

        // Connect the tabLayout to the view pager
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            val resource = CATEGORIES[position].stringResource
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
                launchSearchIntent()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showSavedCocktails() {
        viewPager.currentItem = SAVED_PAGE_INDEX
    }

    override fun navigateTo(cocktailRecipe: CocktailRecipe) {
        val intent = Intent(this, DetailedCocktailActivity::class.java)
        intent.putExtra(DetailedCocktailActivity.EXTRA_RECIPE, cocktailRecipe)
        startActivity(intent)
    }

    private fun launchSearchIntent() {
        val intent = Intent(this, SearchCocktailActivity::class.java)
        startActivity(intent)
    }

}