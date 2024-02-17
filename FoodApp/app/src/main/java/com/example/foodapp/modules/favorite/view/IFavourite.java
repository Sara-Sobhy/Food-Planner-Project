package com.example.foodapp.modules.favorite.view;

import androidx.lifecycle.LiveData;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IFavourite {
    void showMeal(Flowable<List<Meal>> mealList);

}
