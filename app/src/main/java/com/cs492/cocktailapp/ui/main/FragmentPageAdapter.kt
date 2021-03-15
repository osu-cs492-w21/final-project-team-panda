package com.cs492.cocktailapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cs492.cocktailapp.data.BrowseCategory
import com.cs492.cocktailapp.data.Tab

class FragmentPageAdapter(
        activity: AppCompatActivity,
        private val tabs: Array<Tab>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        when (tabs[position]) {
            Tab.New -> return BrowseFragment.newInstance(BrowseCategory.New)
            Tab.Popular -> return BrowseFragment.newInstance(BrowseCategory.Popular)
            Tab.Random -> return BrowseFragment.newInstance(BrowseCategory.Random)
            Tab.Saved -> return SavedFragment()
        }
    }
}