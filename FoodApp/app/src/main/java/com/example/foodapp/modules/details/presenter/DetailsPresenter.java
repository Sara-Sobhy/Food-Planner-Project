package com.example.foodapp.modules.details.presenter;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.details.view.DetailsInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

public class DetailsPresenter implements NetworkCallBack {
    AppRemoteDataSource appRemoteDataSource;
    DetailsInterface detailsInterface;

    public DetailsPresenter( DetailsInterface detailsInterface,String id) {
        this.appRemoteDataSource = AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        this.detailsInterface = detailsInterface;
        appRemoteDataSource.getMealDetail(id);
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
}
