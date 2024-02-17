package com.example.foodapp.network;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRemoteDataSource {
    Retrofit retrofit;
    Network network;
    NetworkCallBack networkCallBack;
    final static String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static AppRemoteDataSource instance = null;
    private AppRemoteDataSource(){
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        network=retrofit.create(Network.class);
    }
    public void setNetworkCallBack(NetworkCallBack networkCallBack){
        this.networkCallBack=networkCallBack;
    }
    public static AppRemoteDataSource getInstance() {
        if(instance == null){
            instance=new AppRemoteDataSource();
        }
        return instance;
    }
    public Observable<FoodCategoryResponse> getMealCategories()
    {
        Log.i("TAG", "getMealCategories: dddddddddddddddddddddddd");
        Observable<FoodCategoryResponse> call= network.getCategories();
        return call.subscribeOn(Schedulers.io());

    }
    public Observable<FoodCountryResponse> getCountries()
    {
        Observable<FoodCountryResponse> call= network.getCountries();
        return call.subscribeOn(Schedulers.io());
    }
    public Observable<MealsResponse> getRandomMeal()
    {
        Observable<MealsResponse> call=network.getMealOfTheDay();
        return call.subscribeOn(Schedulers.io());
    }

    public Observable<MealsResponse> getMealList(String category)
    {
        Observable<MealsResponse> call=network.getMealByCategory(category);
        return call.subscribeOn(Schedulers.io());


    }

    public Observable<MealsResponse> getMealByArea(String area){

        Observable<MealsResponse> call=network.getMealByArea(area);
        return call.subscribeOn(Schedulers.io());


    }

    public Observable<MealsResponse> getMealDetail(String id){
        Observable<MealsResponse> call=network.getMealDetail(id);
        return call.subscribeOn(Schedulers.io());
    }
}
