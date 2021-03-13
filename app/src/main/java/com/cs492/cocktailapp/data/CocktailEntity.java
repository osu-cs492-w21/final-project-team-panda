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

    public CocktailEntity(int id, String name, String glass, String instructions, String imageUrl) {
        this.id = id;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGlass(String glass) { this.glass = glass; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

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
}
