package com.cs492.cocktailapp.ui.main;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cs492.cocktailapp.R;
import com.cs492.cocktailapp.data.CocktailRecipe;

public class DetailedCocktailActivity extends AppCompatActivity {

    private CocktailRecipe cocktail;
    private final static String TAG = DetailedCocktailActivity.class.getSimpleName();
    public static final String EXTRA_RECIPE = "CocktailRecipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_cocktail);

        Intent intent =  getIntent();
        if(intent != null && intent.hasExtra(EXTRA_RECIPE)) {
            this.cocktail = (CocktailRecipe)intent.getSerializableExtra(EXTRA_RECIPE);
            //Log.d(TAG, "Got cocktail with name: " + cocktail.getDrinkName());

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
        }

    }
}