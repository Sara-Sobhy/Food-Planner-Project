package com.example.foodapp.modules.home.view;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;

import java.util.List;

public interface HomeInterface {
    public void showCategories(List<FoodCategory> categoryList);
    public void showCategoriesError(Throwable t);
    public void showCountries(List<FoodCountryResponse.FoodCountry> foodCountryList);
    public void showCountriesError(Throwable throwable);
    public void showRandomMeal(Meal meal);
    public void showRandomMealError(Throwable throwable);
}
