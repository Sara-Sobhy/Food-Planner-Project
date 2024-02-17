package com.example.foodapp.model;

import androidx.lifecycle.LiveData;

import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepository implements RepositoryInterface{

    private LocalDataSource localDataSource;
    private AppRemoteDataSource appRemoteDataSource;
    private static MealRepository instance=null;

    public MealRepository(LocalDataSource localDataSource, AppRemoteDataSource appRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.appRemoteDataSource = appRemoteDataSource;
    }
    public static MealRepository getInstance(LocalDataSource localDataSource,AppRemoteDataSource remoteDataSource) {
        if(instance==null){
            instance = new MealRepository(localDataSource,remoteDataSource);
        }
        return instance;
    }

    @Override
    public Observable<MealsResponse> getAllMeals(String id) {
        return appRemoteDataSource.getMealDetail(id);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeal() {
        return localDataSource.getMealsForUser();
    }

    @Override
    public void insertMeal(Meal meal) {
        localDataSource.insert(meal);

    }

    @Override
    public void deleteMeal(Meal meal) {
       localDataSource.delete(meal);
    }
}
