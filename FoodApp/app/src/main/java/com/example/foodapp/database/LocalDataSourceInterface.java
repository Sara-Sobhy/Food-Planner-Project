package com.example.foodapp.database;

import androidx.lifecycle.LiveData;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalDataSourceInterface {
    public Flowable<List<Meal>> getMealsForUser();
    public void insert(Meal meal);
    public void delete(Meal meal);
}
