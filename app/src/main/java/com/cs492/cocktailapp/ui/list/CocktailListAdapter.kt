package com.cs492.cocktailapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs492.cocktailapp.R
import com.cs492.cocktailapp.data.CocktailListItemSize
import com.cs492.cocktailapp.data.CocktailRecipe

class CocktailListAdapter(private val size: CocktailListItemSize) : RecyclerView.Adapter<CocktailViewHolder>() {

    var cocktailRecipeList: ArrayList<CocktailRecipe> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onClickHandler: ((CocktailRecipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (size) {
            CocktailListItemSize.Large -> {
                val itemView = inflater.inflate(R.layout.item_cocktail_large, parent, false)
                return LargeCocktailItem(itemView)
            }
            CocktailListItemSize.Small -> {
                val itemView = inflater.inflate(R.layout.item_cocktail_small, parent, false)
                return SmallCocktailItem(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(cocktailRecipeList[position])
    }

    override fun getItemCount(): Int {
        return cocktailRecipeList.size
    }

    inner class LargeCocktailItem(itemView: View) : CocktailViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.browseCategoryListItemCocktailName)
        private val image = itemView.findViewById<ImageView>(R.id.browseCategoryListItemCocktailImage)

        override fun bind(data: CocktailRecipe) {
            name.text = data.drinkName

            Glide.with(itemView.context)
                    .load(data.drinkImage)
                    .into(image)
        }

        init {
            itemView.setOnClickListener {
                onClickHandler?.invoke(cocktailRecipeList[adapterPosition])
            }
        }
    }

    inner class SmallCocktailItem(itemView: View) : CocktailViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.smallCocktailItemName)
        private val image = itemView.findViewById<ImageView>(R.id.smallCocktailItemImage)
        private val subtitle = itemView.findViewById<TextView>(R.id.smallCocktailItemSubtitle)

        override fun bind(data: CocktailRecipe) {
            name.text = data.drinkName.trim()
            subtitle.text = data.ingredientSummary.trim()

            Glide.with(itemView.context)
                    .load(data.drinkImage)
                    .into(image)
        }

        init {
            itemView.setOnClickListener {
                onClickHandler?.invoke(cocktailRecipeList[adapterPosition])
            }
        }
    }
}