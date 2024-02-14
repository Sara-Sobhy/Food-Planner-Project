package com.example.foodapp.modules.ListOfMeals.presenter;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.ListOfMeals.view.ListMealsNameInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.Arrays;
import java.util.List;

public class ListOfMealsPresenter implements NetworkCallBack {

  ListMealsNameInterface listMealsNameInterface;
  AppRemoteDataSource appRemoteDataSource;


  public ListOfMealsPresenter(ListMealsNameInterface listMealsNameInterface,String categories){

      this.listMealsNameInterface= listMealsNameInterface;
      appRemoteDataSource=AppRemoteDataSource.getInstance();
      appRemoteDataSource.setNetworkCallBack(this);


  }

  public void getMealsbyCategory(String categories){
    appRemoteDataSource.getMealList(categories);
  }
  public void getMealsbyarea(String area){
    appRemoteDataSource.getMealByArea(area);
  }
  private boolean isCategory(String categories) {
    List<String> categoryNames = Arrays.asList("Beef", "Chicken", "Seafood");
    return categoryNames.contains(categories);
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
       listMealsNameInterface.showCategoriesName( mealList);
    }

    @Override
    public void onGetCategoriesListNameFailure(Throwable throwable) {
      listMealsNameInterface.showCategoriesNameError(throwable);
    }

  @Override
  public void onGetAreaListNameSuccess(List<Meal> mealList) {
    listMealsNameInterface.showMealByArea(mealList);
  }

  @Override
  public void onGetAreaListNameFailure(Throwable throwable) {
     listMealsNameInterface.showMealByAreaError(throwable);
  }
}
