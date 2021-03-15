package com.cs492.cocktailapp.api;

import com.cs492.cocktailapp.BuildConfig;
import com.cs492.cocktailapp.data.BrowseCategory;
import com.cs492.cocktailapp.data.CocktailItem;
import com.cs492.cocktailapp.data.CocktailList;
import com.cs492.cocktailapp.data.CocktailRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailRepository {
    private static final String TAG = CocktailRepository.class.getSimpleName();
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/";
    private final CocktailService cocktailService;

    static private String getBaseUrl() {
        // This value defaults to "1" unless you have the "api.properties" file in the root
        // directory of the project (see Build.gradle (Module)).
        String providedKey = BuildConfig.COCKTAIL_DB_API_KEY;
        if (providedKey.equals("1")) {
            return BASE_URL + "v1/1/";
        } else {
            // If there was a key provided we need to use v2
            return BASE_URL + "v2/" + providedKey + "/";
        }
    }

    public CocktailRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                // If we want to add caching we can add `.client(client)` but it opens this
                // giant dependency-injection can of worms (ask Julian for details).
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cocktailService = retrofit.create(CocktailService.class);
    }

    // Converts a BrowseCategory to the appropriate API path parameter
    private String categoryPathParameter(BrowseCategory category) {
        if (category == BrowseCategory.New) {
            return "latest";
        } else if (category == BrowseCategory.Popular) {
            return "popular";
        } else if (category == BrowseCategory.Random) {
            return "randomselection";
        } else {
            // Saved doesn't have an API
            return null;
        }

    }

    private ArrayList<CocktailRecipe> convertCocktailItemsToRecipes(List<CocktailItem> items){
        ArrayList<CocktailRecipe> recipes = new ArrayList<>();

        // TODO: Implement better way to fix null error
        // If you don't check for it to be null then the search function will break when there are no matching results (returns null cocktails)
        if(items != null) {
            for (CocktailItem item : items) {
                CocktailRecipe obj = new CocktailRecipe(
                        item.getDrinkName(),
                        Integer.parseInt(item.getDrinkId()),
                        item.getDrinkImage(),
                        item.getDrinkInstructions(),
                        item.getDrinkGlass(),
                        item.getMeasureIngredients()
                );
                recipes.add(obj);
            }
        }
        return recipes;
    }

    // @Param route: This accepts a method that takes a `String` and returns a `Call<CocktailList>`.
    // @Param query: The String parameter to pass to the api method.
    private CompletableFuture<ArrayList<CocktailRecipe>> getListFromQuery(Function<String,Call<CocktailList>> route, String query) {
        CompletableFuture<ArrayList<CocktailRecipe>> result = new CompletableFuture<>();

        Call<CocktailList> request = route.apply(query);

        request.enqueue(new Callback<CocktailList>() {
            @Override
            public void onResponse(@NotNull Call<CocktailList> call, @NotNull Response<CocktailList> response) {
                if (response.code() == 200) {
                    result.complete(
                            convertCocktailItemsToRecipes(response.body().getCocktailRecipes())
                    );
                } else {
                    result.completeExceptionally(new Exception("Server error"));
                }
            }

            @Override
            public void onFailure(@NotNull Call<CocktailList> call, @NotNull Throwable t) {
                result.completeExceptionally(t);
            }
        });

        return result;
    }

    public CompletableFuture<ArrayList<CocktailRecipe>> getSearchResults(String query) {
        return getListFromQuery(this.cocktailService::searchCocktailByName, query);
    }

    public CompletableFuture<ArrayList<CocktailRecipe>> getBrowse(BrowseCategory category) {
        String categoryParameter = categoryPathParameter(category);
        return getListFromQuery(this.cocktailService::getBrowse, categoryParameter);
    }

}
