package com.example.foodapp.modules.favorite.presenter;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.modules.favorite.view.IFavourite;
import com.example.foodapp.modules.home.view.HomeInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

public class FavoritePresenter implements NetworkCallBack {

    IFavourite iFavourite;
    MealRepository mealRepository;
    Context context;
    SharedPreferences sp ;
    String email;
    public FavoritePresenter(IFavourite iFavourite, MealRepository mealRepository,Context context) {

        this.iFavourite = iFavourite;
        this.mealRepository = mealRepository;
        this.context=context;
        sp = context.getSharedPreferences("email", Context.MODE_PRIVATE);
        sp.getString("userEmail","");

    }
    public void getProducts()
    {
        iFavourite.showMeal(mealRepository.getStoredMeal());////////////////////////////

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

    }

    @Override
    public void onGetMealFailure(Throwable throwable) {

    }
}
