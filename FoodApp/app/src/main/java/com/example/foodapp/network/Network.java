package com.example.foodapp.network;



import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Network {
    @GET("categories.php")
    public Observable<FoodCategoryResponse> getCategories();
    @GET("list.php?a=list")
    public Observable<FoodCountryResponse> getCountries();
    @GET("random.php")
    public Observable<MealsResponse> getMealOfTheDay();

    @GET("filter.php")
    Observable<MealsResponse> getMealByCategory(@Query("c") String categoryName);

    @GET("filter.php")
    Observable<MealsResponse> getMealByArea(@Query("a") String categoryName);

    @GET("lookup.php")
    Observable<MealsResponse> getMealDetail(@Query("i") String mealDetail);
}
