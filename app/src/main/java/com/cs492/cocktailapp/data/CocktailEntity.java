package com.cs492.cocktailapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "cocktails")
public class CocktailEntity implements Serializable {

    @PrimaryKey
    @NonNull
    public int id;
    public String name;
    public String glass;
    public String instructions;
    public String imageUrl;
    public boolean savedStatus;

    public CocktailEntity() { }

    // setters - required to have one for each member variable
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGlass(String glass) { this.glass = glass; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setSavedStatus(boolean status) { this.savedStatus = status; }

    // getters - required to have one for each member variable
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getGlass() {
        return this.glass;
    }
    public String getInstructions() {
        return this.instructions;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public boolean getSavedStatus() { return this.savedStatus; }
}
