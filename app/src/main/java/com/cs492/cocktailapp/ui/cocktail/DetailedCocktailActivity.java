package com.cs492.cocktailapp.ui.cocktail;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        this.isFavorited = false;
        // TO DO: Initialize database ViewModel for favorited drinks
        // this.viewModel = ....

        Intent intent =  getIntent();
        if(intent != null && intent.hasExtra(EXTRA_RECIPE)) {
            this.cocktail = (CocktailRecipe)intent.getSerializableExtra(EXTRA_RECIPE);

            setTitle(cocktail.getDrinkName());

            TextView cocktailName = findViewById(R.id.detailed_cocktail_name);
            TextView cocktailGlass = findViewById(R.id.detailed_cocktail_glass);
            TextView cocktailInstructions = findViewById(R.id.detailed_cocktail_instructions);
            ImageView cocktailImage = findViewById(R.id.detailed_cocktail_image);

            cocktailName.setText(cocktail.getDrinkName());
            cocktailGlass.setText(cocktail.getDrinkGlass());
            cocktailInstructions.setText(cocktail.getDrinkInstructions());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cocktail_detail_menu, menu);

        // TO DO: check if drink is favorited in database

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                toggleDrinkFavorite(item);
                return true;
            case R.id.action_share:
                shareCocktail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // TO DO: Hook up favorite button with database functions
    private void toggleDrinkFavorite(MenuItem menuItem) {
        if (this.cocktail != null) {
            this.isFavorited = !this.isFavorited;
            menuItem.setChecked(this.isFavorited);
            if (this.isFavorited) {
                menuItem.setIcon(R.drawable.ic_favorite);
                //this.viewModel.insertFavoriteCocktail(this.cocktail);
            } else {
                menuItem.setIcon(R.drawable.ic_favorite_border);
                //this.viewModel.deleteFavoriteCocktail(this.cocktail);
            }
        }
    }

    private void shareCocktail() {
        if (this.cocktail != null) {
            String link = "https://www.thecocktaildb.com/drink/" + String.valueOf(cocktail.getDrinkId());
            String shareText = "Check out this delicious cocktail I found: " + link;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(intent, null);
            startActivity(chooserIntent);
        }
    }



}