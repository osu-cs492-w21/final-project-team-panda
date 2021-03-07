package com.cs492.cocktailapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cs492.cocktailapp.R
import java.io.Serializable

enum class BrowseCategory {
    New,
    Popular,
    Random,
    Saved;

    val stringResource: Int
        get() = when (this) {
                New -> R.string.browse_new
                Popular -> R.string.browse_popular
                Random -> R.string.browse_random
                Saved -> R.string.browse_saved
        }

}

/*
 * The caller *MUST* put a BrowseCategory with key CATEGORY_ARGUMENT in
 * arguments immediately after instantiation or else a crash will occur.
 */
class BrowseFragment : Fragment() {

    private lateinit var pageViewModel: BrowseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ⚠️ This will crash if the argument is not passed
        val category = requireArguments().getSerializable(CATEGORY_ARGUMENT) as BrowseCategory

        // We want to associate a different ViewModel with each Fragment to 'get' by the category.
        pageViewModel = ViewModelProvider(this).get(category.toString(), BrowseViewModel::class.java).apply {
            setCategory(category)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_browse, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)

        pageViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val CATEGORY_ARGUMENT = "category"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(category: BrowseCategory): BrowseFragment {
            return BrowseFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CATEGORY_ARGUMENT, category)
                }
            }
        }
    }
}