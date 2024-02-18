package com.example.foodapp.modules.details.presenter;

import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.details.view.DetailsInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class DetailsPresenter implements NetworkCallBack {
    AppRemoteDataSource appRemoteDataSource;
    DetailsInterface detailsInterface;
    MealRepository mealRepository;

    public DetailsPresenter( DetailsInterface detailsInterface,MealRepository mealRepository,String id) {
        this.appRemoteDataSource = AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        this.detailsInterface = detailsInterface;
        this.mealRepository=mealRepository;
        getAllMeals(id);
//        appRemoteDataSource.getMealDetail(id);
    }
    public void insertPlan(Plane plane){
        mealRepository.insertMealPlan(plane);
    }
    public void getAllMeals(String id)
    {
        mealRepository.getAllMeals(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsResponse -> detailsInterface.showMealDetails(mealsResponse.getMealList()) );
    }


    @Override
    public void onGetCategoriesSuccess(List<FoodCategory> categoryList) {

    }

    @Override
    public void onGetCategoriesFailure(Throwable error) {

    }

    @Override
    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries) {

    }

    @Override
    public void onGetCountriesFailure(Throwable throwable) {

    }

    @Override
    public void onGetRandomMealSuccessful(Meal meal) {

    }

    @Override
    public void onGetRandomMealFailure(Throwable throwable) {

    }

    @Override
    public void onGetCategoriesListNameSuccess(List<Meal> mealList) {

    }

    @Override
    public void onGetCategoriesListNameFailure(Throwable throwable) {

    }

    @Override
    public void onGetAreaListNameSuccess(List<Meal> mealList) {

    }

    @Override
    public void onGetAreaListNameFailure(Throwable throwable) {

    }

    @Override
    public void onGetMealSuccess(List<Meal> meal) {

        detailsInterface.showMealDetails(meal);
    }

    @Override
    public void onGetMealFailure(Throwable throwable) {
          detailsInterface.showMealDetailsError(throwable);
    }

    public void insert(Meal meal){
        mealRepository.insertMeal(meal);
    }
}
