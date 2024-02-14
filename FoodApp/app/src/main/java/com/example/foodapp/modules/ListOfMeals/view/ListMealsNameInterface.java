package com.example.foodapp.modules.ListOfMeals.view;

import com.example.foodapp.model.Meal;

import java.util.List;

public interface ListMealsNameInterface {

    public void showCategoriesName(List<Meal> meal);
    public void showCategoriesNameError(Throwable throwable);

    public void showMealByArea(List<Meal> meal);
    public void showMealByAreaError(Throwable throwable);
}
