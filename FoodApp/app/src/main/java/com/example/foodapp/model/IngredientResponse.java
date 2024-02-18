package com.example.foodapp.model;

import java.util.List;

public class IngredientResponse {
    List<Ingredient> meals;

    public IngredientResponse(List<Ingredient> ingredientList) {
        this.meals = ingredientList;
    }

    public List<Ingredient> getIngredientList() {
        return meals;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.meals = ingredientList;
    }
}
