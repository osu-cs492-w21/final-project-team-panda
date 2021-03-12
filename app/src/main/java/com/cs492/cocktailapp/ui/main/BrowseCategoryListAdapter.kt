package com.cs492.cocktailapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs492.cocktailapp.R
import com.cs492.cocktailapp.data.CocktailRecipe


class BrowseCategoryListAdapter : RecyclerView.Adapter<BrowseCategoryListAdapter.BrowseCategoryListItem>() {

    var cocktailRecipeList: ArrayList<CocktailRecipe> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onClickHandler: ((CocktailRecipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseCategoryListItem {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_browse_category, parent, false)
        return BrowseCategoryListItem(itemView)
    }

    override fun onBindViewHolder(holder: BrowseCategoryListItem, position: Int) {
        holder.bind(cocktailRecipeList[position])
    }

    override fun getItemCount(): Int {
        return cocktailRecipeList.size
    }

    inner class BrowseCategoryListItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.browseCategoryListItemCocktailName)
        private val image = itemView.findViewById<ImageView>(R.id.browseCategoryListItemCocktailImage)

        fun bind(cocktailRecipe: CocktailRecipe) {
            name.text = cocktailRecipe.drinkName

            Glide.with(itemView.context)
                    .load(cocktailRecipe.drinkImage)
                    .into(image)
        }

        init {
            itemView.setOnClickListener {
                onClickHandler?.invoke(cocktailRecipeList[adapterPosition])
            }
        }
    }
}