package com.cs492.cocktailapp.ui.cocktail;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private DetailedCocktailViewModel viewModel;
    private boolean isFavorited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_cocktail);

        this.isFavorited = false;

        // Initialize database ViewModel for favorited drinks
        this.viewModel = new ViewModelProvider(
             this,
             new ViewModelProvider.AndroidViewModelFactory(
                    getApplication()
             )
        ).get(DetailedCocktailViewModel.class);

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

        // Check if drink is saved in database
        this.viewModel.getSavedCocktailById(this.cocktail.getDrinkId()).observe(
                this,
                new Observer<CocktailRecipe>() {
                    @Override
                    public void onChanged(CocktailRecipe cocktail) {
                        if(cocktail != null) {
                            isFavorited = true;
                            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_favorite);
                        }
                        else {
                            isFavorited = false;
                            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_favorite_border);
                        }
                    }
                }
        );
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

    private void toggleDrinkFavorite(MenuItem menuItem) {
        if (this.cocktail != null) {
            this.isFavorited = !this.isFavorited;
            menuItem.setChecked(this.isFavorited);
            if (this.isFavorited) {
                this.viewModel.insertCocktail(this.cocktail);
            } else {
                this.viewModel.deleteCocktail(this.cocktail);
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