package com.cs492.cocktailapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cs492.cocktailapp.model.BrowseCategory

class BrowseFragmentAdapter(
        activity: AppCompatActivity,
        private val categories: Array<BrowseCategory>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return BrowseFragment.newInstance(categories[position])
    }
}