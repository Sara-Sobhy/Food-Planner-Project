package com.example.foodapp.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.details.view.DetailsMealFragment;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalDataSource implements LocalDataSourceInterface{
    private MealDAO mealDAO;
    private PlaneDAO planeDAO;
    private static LocalDataSource instance=null;
    private Flowable<List<Meal>> mealList;
    private LocalDataSource(Context context){
        AppDataBase appDataBase= AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= appDataBase.getMealDAO();
        mealList = mealDAO.getMealsForUser();

        planeDAO=appDataBase.getPlanDAO();

    }
    public static LocalDataSource getInstance(Context context) {
        if(instance == null){
            instance=new LocalDataSource(context);
        }
        return instance;
    }



    @Override
    public Flowable<List<Meal>> getMealsForUser() {
        return mealList;
    }

    @Override
    public void insert(Meal meal) {
        new Thread(() -> {
            mealDAO.insert(meal);
        }).start();
    }

    @Override
    public void delete(Meal meal) {
        new Thread(() -> {
            mealDAO.delete(meal);
        }).start();
    }

    @Override
    public Flowable<List<Plane>> getPlans() {
        return planeDAO.getPlansForUser();
    }

    @Override
    public void insertPlan(Plane plane) {
        new Thread(() -> {
            planeDAO.insertPlan(plane);
        }).start();

    }

    @Override
    public void deletePan(Plane plane) {
        new Thread(() -> {
            planeDAO.deletePlan(plane);
        }).start();
    }
}
