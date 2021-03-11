package com.cs492.cocktailapp.ui.main;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cs492.cocktailapp.R;
import com.cs492.cocktailapp.data.CocktailRecipe;

public class DetailedCocktailActivity extends AppCompatActivity {

    private CocktailRecipe cocktail;
    private final static String TAG = DetailedCocktailActivity.class.getSimpleName();
    public static final String EXTRA_RECIPE = "CocktailRecipe";

    // Ingredients list
    private RecyclerView ingredientListRV;
    private IngredientAdapter ingredientAdapter;

    // TO DO: Add database stuff for favorited drinks
    // private < (insert view model name) > viewModel
    private boolean isFavorited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_cocktail);

        Intent intent =  getIntent();
        if(intent != null && intent.hasExtra(EXTRA_RECIPE)) {
            this.cocktail = (CocktailRecipe)intent.getSerializableExtra(EXTRA_RECIPE);

            setTitle(cocktail.getDrinkName() + " Recipe");

            TextView cocktailName = findViewById(R.id.detailed_cocktail_name);
            TextView cocktailGlass = findViewById(R.id.detailed_cocktail_glass);
            TextView cocktailInstructions = findViewById(R.id.detailed_cocktail_instructions);
            ImageView cocktailImage = findViewById(R.id.detailed_cocktail_image);

            cocktailName.setText(cocktail.getDrinkName());
            cocktailGlass.setText("Glass: " + cocktail.getDrinkGlass());
            cocktailInstructions.setText("Instructions: \n" + cocktail.getDrinkInstructions());
            Glide.with(this)
                    .load(this.cocktail.getDrinkImage())
                    .into(cocktailImage);

            // RecyclerView set up
            this.ingredientListRV = findViewById(R.id.rv_ingredient_list);
            this.ingredientListRV.setLayoutManager(new LinearLayoutManager(this));
            this.ingredientListRV.setHasFixedSize(true);

            this.ingredientAdapter = new IngredientAdapter();
            ingredientListRV.setAdapter(ingredientAdapter);

            ingredientAdapter.updateIngredientListData(this.cocktail.getIngredientList());

        }
    }

    // TO DO: Add click listener for favorite button
}