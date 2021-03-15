package com.cs492.cocktailapp.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs492.cocktailapp.R
import com.cs492.cocktailapp.data.CocktailListItemSize
import com.cs492.cocktailapp.ui.list.CocktailListAdapter

class SavedFragment : Fragment() {

    private lateinit var viewModel: SavedViewModel
    private val adapter = CocktailListAdapter(CocktailListItemSize.Small)

    private lateinit var recyclerView: RecyclerView
    private lateinit var infoView: ConstraintLayout

    // Source: "Kotlin Fragment to Activity Communication Example" by zacharymikel
    // https://gist.github.com/zacharymikel/40aa61b2ff4d0b1ae267212d7dd965e5
    private var listener: CocktailFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CocktailFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context does not implement `BrowseFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.application?.let { application ->
            viewModel = ViewModelProvider(this, AndroidViewModelFactory(application))
                    .get(SavedViewModel::class.java)
        } ?: run {
            Log.e("SavedFragment", "Unable to acquire application in `onCreate`")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_saved, container, false)

        // Grab all the UI elements
        recyclerView = root.findViewById(R.id.savedRecyclerView)
        infoView = root.findViewById(R.id.savedInfoView)

        // Set up recycler view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        // Click listener
        adapter.onClickHandler = listener!!::navigateTo

        // Connect view model to the recycler view
        viewModel.savedCocktails.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                recyclerView.visibility = View.INVISIBLE
                infoView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                infoView.visibility = View.INVISIBLE

                adapter.cocktailRecipeList = it
            }
        })

        return root
    }
}