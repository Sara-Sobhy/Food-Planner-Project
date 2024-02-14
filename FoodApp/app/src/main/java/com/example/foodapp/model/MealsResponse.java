package com.example.foodapp.model;

import java.util.List;

public class MealsResponse {
    List<Meal> meals;

    public MealsResponse(List<Meal> mealList) {
        this.meals = mealList;
    }

    public List<Meal> getMealList() {
        return meals;
    }

    public void setMealList(List<Meal> mealList) {
        this.meals = mealList;
    }
}
