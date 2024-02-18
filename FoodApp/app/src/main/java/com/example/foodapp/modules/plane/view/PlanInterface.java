package com.example.foodapp.modules.plane.view;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plane;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanInterface {
    void showMealFromPlan(Flowable<List<Plane>> mealList);
    public void removeMealFromPlan(Plane plane);
}
