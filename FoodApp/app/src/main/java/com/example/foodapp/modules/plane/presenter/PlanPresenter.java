package com.example.foodapp.modules.plane.presenter;

import android.content.Context;

import com.example.foodapp.model.MealRepository;
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.plane.view.PlanInterface;

public class PlanPresenter {
    PlanInterface planInterface;
    MealRepository mealRepository;

    public PlanPresenter(PlanInterface planInterface, MealRepository mealRepository, Context context) {
        this.planInterface = planInterface;
        this.mealRepository = mealRepository;
    }

    public void getProducts()
    {
        planInterface.showMealFromPlan(mealRepository.getStoredMealPlan());

    }
    public void removeMeal(Plane plane){
        mealRepository.deleteMealPlan(plane);
    }
}
