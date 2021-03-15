package com.cs492.cocktailapp.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cs492.cocktailapp.R;
import com.cs492.cocktailapp.data.CocktailRecipe;

import java.util.ArrayList;

public class CocktailSearchAdapter extends RecyclerView.Adapter<CocktailSearchAdapter.SearchResultViewHolder> {
    private ArrayList<CocktailRecipe> searchResultsList;
    private final OnSearchResultClickListener resultClickListener;

    interface OnSearchResultClickListener {
        void onSearchResultClicked(CocktailRecipe recipe);
    }

    public CocktailSearchAdapter(OnSearchResultClickListener listener) {
        this.resultClickListener = listener;
    }

    public void updateSearchResults(ArrayList<CocktailRecipe> searchResultsList) {
        this.searchResultsList = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.searchResultsList != null) {
            return this.searchResultsList.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cocktail_small, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.bind(this.searchResultsList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView searchResultTV;
        private final ImageView cocktailImage;
        private final TextView subtitle;


        SearchResultViewHolder(View itemView) {
            super(itemView);
            this.searchResultTV = itemView.findViewById(R.id.smallCocktailItemName);
            this.cocktailImage = itemView.findViewById(R.id.smallCocktailItemImage);
            this.subtitle = itemView.findViewById(R.id.smallCocktailItemSubtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultClickListener.onSearchResultClicked(
                            searchResultsList.get(getAdapterPosition())
                    );
                }
            });
        }

        void bind(CocktailRecipe recipe) {
            this.searchResultTV.setText(recipe.getDrinkName());
            this.subtitle.setText(recipe.getIngredientSummary());
            Glide.with(itemView)
                    .load(recipe.getDrinkImage())
                    .into(cocktailImage);
        }
    }

}
