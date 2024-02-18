package com.example.foodapp.modules.search.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.IngredientResponse;
import com.example.foodapp.model.QueryResult;
import com.example.foodapp.modules.search.view.SearchInterface;
import com.example.foodapp.network.AppRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    AppRemoteDataSource appRemoteDataSource;
    SearchInterface searchInterface;
    Observable<IngredientResponse> ingredientsObservable;
    Observable<FoodCountryResponse> countriesObservable;
    Observable<FoodCategoryResponse> categoriesObservable;
    Observable<QueryResult> mealsObservable;
    public SearchPresenter(SearchInterface searchInterface)
    {
        appRemoteDataSource = AppRemoteDataSource.getInstance();
        this.searchInterface=searchInterface;
        loadData();
    }
    public void loadData()
    {
        ingredientsObservable=appRemoteDataSource.getIngredientList();
        countriesObservable=appRemoteDataSource.getCountries();
        categoriesObservable=appRemoteDataSource.getMealCategories();

    }
    public void getSearch(String query)
    {/*
        IngredientResponse { list<ingredient> ,name , id }
        ingredientsObservable<IngredientResponse>  IngredientResponse -> flatiterable
        ingredientsObservable<list<ingredient>>.map
        ingredientsObservable<QueryResult>
     */
        Observable<QueryResult> obs1=ingredientsObservable
                .flatMapIterable(ingredientResponse -> ingredientResponse.getIngredientList())
                .map(ingredient -> new QueryResult(ingredient.getStrIngredient(),"ingredient"))
                .subscribeOn(Schedulers.io());
        Observable<QueryResult> obs2=countriesObservable
                .flatMapIterable(foodCountryResponse -> foodCountryResponse.getMeals())
                .map(foodCountry -> new QueryResult(foodCountry.getStrArea(),"country"))
                .subscribeOn(Schedulers.io());
        Observable<QueryResult> obs3=categoriesObservable
                .flatMapIterable(foodCategoryResponse -> foodCategoryResponse.getList())
                .map(foodCategory -> new QueryResult(foodCategory.getStrCategory(),"category"))
                .subscribeOn(Schedulers.io());
        Observable<QueryResult> mealsObservable= appRemoteDataSource.getMealsByName(query)
                .flatMapIterable(mealsResponse -> mealsResponse.getMealList())
                .map(meal -> new QueryResult(meal.getStrMeal(),"meal"))
                .subscribeOn(Schedulers.io());
        Observable<QueryResult> bigObservable= Observable.merge(obs1,obs2,obs3,mealsObservable);
        bigObservable
                .filter(s -> s.getResult().toLowerCase().contains(query))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<QueryResult>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<QueryResult> stringList) {
                        Log.d("TAG", "onSuccess: "+stringList);
                        searchInterface.showResult(stringList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
