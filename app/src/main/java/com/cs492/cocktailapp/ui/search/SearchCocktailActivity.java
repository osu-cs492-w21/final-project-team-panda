package com.cs492.cocktailapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs492.cocktailapp.R;
import com.cs492.cocktailapp.data.CocktailRecipe;
import com.cs492.cocktailapp.ui.cocktail.DetailedCocktailActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchCocktailActivity extends AppCompatActivity implements CocktailSearchAdapter.OnSearchResultClickListener {
    private final static String TAG = SearchCocktailActivity.class.getSimpleName();

    private RecyclerView searchResultsRV;
    private EditText searchBoxET;

    private CocktailSearchAdapter cocktailSearchAdapter;
    private CocktailSearchViewModel cocktailSearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.searchBoxET = findViewById(R.id.et_search_box);
        this.searchResultsRV = findViewById(R.id.rv_search_results);

        this.searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        this.searchResultsRV.setHasFixedSize(true);

        this.cocktailSearchAdapter = new CocktailSearchAdapter(this);
        this.searchResultsRV.setAdapter(this.cocktailSearchAdapter);

        this.cocktailSearchViewModel = new ViewModelProvider(this).get(CocktailSearchViewModel.class);

        this.cocktailSearchViewModel.getSearchResults().observe(this, new Observer<ArrayList<CocktailRecipe>>() {
            @Override
            public void onChanged(ArrayList<CocktailRecipe> cocktailRecipes) {
                cocktailSearchAdapter.updateSearchResults(cocktailRecipes);
            }
        });

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    cocktailSearchViewModel.loadSearchResultsForName(searchQuery);
                }
            }
        });
    }

    @Override
    public void onSearchResultClicked(CocktailRecipe recipe) {
        Intent intent = new Intent(this, DetailedCocktailActivity.class);
        intent.putExtra(DetailedCocktailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }
}
