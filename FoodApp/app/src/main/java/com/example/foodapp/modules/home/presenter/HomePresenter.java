package com.example.foodapp.modules.home.presenter;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;
import com.example.foodapp.modules.home.view.HomeInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenter implements NetworkCallBack{
    HomeInterface homeInterface;
    AppRemoteDataSource appRemoteDataSource;
    public HomePresenter(HomeInterface homeInterface)
    {
        this.homeInterface= homeInterface;
        appRemoteDataSource=AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        getMealCatgory();
        getMealCountry();
        getRandomMeal();

        //appRemoteDataSource.getRandomMeal(String catogry);
    }
    public void getMealCatgory(){
        appRemoteDataSource.getMealCategories().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodCategoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FoodCategoryResponse foodCategoryResponse) {
                       homeInterface.showCategories(foodCategoryResponse.getList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getMealCountry(){
        appRemoteDataSource.getCountries().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodCountryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FoodCountryResponse foodCountryResponse) {
                         homeInterface.showCountries(foodCountryResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void getRandomMeal(){
        appRemoteDataSource.getRandomMeal().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
                        Log.d("TAG", "onNext: "+mealsResponse.getMealList().get(0));
                        homeInterface.showRandomMeal(mealsResponse.getMealList().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

    @Override
    public void onGetMealSuccess(List<Meal> meal) {

    }

    @Override
    public void onGetMealFailure(Throwable throwable) {

    }
}
