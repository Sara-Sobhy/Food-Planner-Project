package com.example.foodapp.modules.details.view;

import com.example.foodapp.model.Meal;

import java.util.List;

public interface DetailsInterface {
    public void showMealDetails(List<Meal> meal);
    public void showMealDetailsError(Throwable throwable);
}
