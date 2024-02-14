package com.example.foodapp.network;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Network {
    @GET("categories.php")
    public Call<FoodCategoryResponse> getCategories();
    @GET("list.php?a=list")
    public Call<FoodCountryResponse> getCountries();
    @GET("random.php")
    public Call<MealsResponse> getMealOfTheDay();

    @GET("filter.php")
    Call<MealsResponse> getMealByCategory(@Query("c") String categoryName);

    @GET("filter.php")
    Call<MealsResponse> getMealByArea(@Query("a") String categoryName);

}
