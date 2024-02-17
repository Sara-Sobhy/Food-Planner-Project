package com.example.foodapp.modules.ListOfMeals.presenter;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;
import com.example.foodapp.modules.ListOfMeals.view.ListMealsNameInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ListOfMealsPresenter implements NetworkCallBack {

  ListMealsNameInterface listMealsNameInterface;
  AppRemoteDataSource appRemoteDataSource;


  public ListOfMealsPresenter(ListMealsNameInterface listMealsNameInterface,String categories){

      this.listMealsNameInterface= listMealsNameInterface;
      appRemoteDataSource=AppRemoteDataSource.getInstance();
      appRemoteDataSource.setNetworkCallBack(this);


  }

  public void getMealsbyCategory(String categories){

    appRemoteDataSource.getMealList(categories)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MealsResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull MealsResponse mealsResponse) {
                      listMealsNameInterface.showCategoriesName(mealsResponse.getMealList());
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
  }
  public void getMealsbyarea(String area){

    appRemoteDataSource.getMealByArea(area)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MealsResponse>() {
              @Override
              public void onSubscribe(@NonNull Disposable d) {

              }

              @Override
              public void onNext(@NonNull MealsResponse mealsResponse) {
                  listMealsNameInterface.showMealByArea(mealsResponse.getMealList());
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

  @Override
  public void onGetMealSuccess(List<Meal> meal) {

  }

  @Override
  public void onGetMealFailure(Throwable throwable) {

  }
}
