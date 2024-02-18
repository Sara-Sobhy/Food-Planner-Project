package com.example.foodapp.model;

import androidx.lifecycle.LiveData;

import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    public Observable<MealsResponse> getAllMeals(String id);
    public Flowable<List<Meal>> getStoredMeal();
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);

    public Flowable<List<Plane>> getStoredMealPlan();
    public void insertMealPlan(Plane plane);
    public void deleteMealPlan(Plane plane);
}
