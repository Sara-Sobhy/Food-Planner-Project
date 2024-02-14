package com.example.foodapp.modules.home.presenter;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.home.view.HomeInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

public class HomePresenter implements NetworkCallBack{
    HomeInterface homeInterface;
    AppRemoteDataSource appRemoteDataSource;
    public HomePresenter(HomeInterface homeInterface)
    {
        this.homeInterface= homeInterface;
        appRemoteDataSource=AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        appRemoteDataSource.getMealCategories();
        appRemoteDataSource.getCountries();
        appRemoteDataSource.getRandomMeal();

        //appRemoteDataSource.getRandomMeal(String catogry);
    }
    @Override
    public void onGetCategoriesSuccess(List<FoodCategory> categoryList) {
        homeInterface.showCategories(categoryList);
    }

    @Override
    public void onGetCategoriesFailure(Throwable error) {
        homeInterface.showCategoriesError(error);
    }

    @Override
    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries) {
        homeInterface.showCountries(foodCountries);
    }

    @Override
    public void onGetCountriesFailure(Throwable throwable) {
        homeInterface.showCountriesError(throwable);
    }

    @Override
    public void onGetRandomMealSuccessful(Meal meal) {
        homeInterface.showRandomMeal(meal);
    }

    @Override
    public void onGetRandomMealFailure(Throwable throwable) {
        homeInterface.showRandomMealError(throwable);
    }

    @Override
    public void onGetCategoriesListNameSuccess(List List) {

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
}
