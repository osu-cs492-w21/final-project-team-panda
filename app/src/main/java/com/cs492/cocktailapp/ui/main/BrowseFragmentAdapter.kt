package com.cs492.cocktailapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BrowseFragmentAdapter(activity: AppCompatActivity, val itemsCount: Int) : FragmentStateAdapter(activity) {

    // TODO: Find a better way to store this configuration?
    companion object {
        val CATEGORIES = arrayOf(
                BrowseCategory.Popular,
                BrowseCategory.New,
                BrowseCategory.Random,
                BrowseCategory.Saved
        )
    }

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return BrowseFragment.newInstance(CATEGORIES[position])
    }
}