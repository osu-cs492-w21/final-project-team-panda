package com.cs492.cocktailapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
            CocktailEntity.class,
            CocktailIngredientsEntity.class
        },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final int NUM_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);

    public abstract SavedCocktailsDao savedCocktailsDao();

    static AppDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "saved-cocktails.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
