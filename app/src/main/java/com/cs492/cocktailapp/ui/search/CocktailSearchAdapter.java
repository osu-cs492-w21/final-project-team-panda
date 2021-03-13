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
import java.util.List;

public class CocktailSearchAdapter extends RecyclerView.Adapter<CocktailSearchAdapter.SearchResultViewHolder> {
    private ArrayList<CocktailRecipe> searchResultsList;
    private OnSearchResultClickListener resultClickListener;

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
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.bind(this.searchResultsList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView searchResultTV;
        private ImageView cocktailImage;


        SearchResultViewHolder(View itemView) {
            super(itemView);
            this.searchResultTV = itemView.findViewById(R.id.searchItemName);
            this.cocktailImage = itemView.findViewById(R.id.searchItemCocktailImage);

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
            Glide.with(itemView)
                    .load(recipe.getDrinkImage())
                    .into(cocktailImage);
        }
    }

}
