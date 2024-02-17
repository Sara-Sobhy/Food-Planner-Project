package com.example.foodapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    //@Query("select * from meal_table")
    //public LiveData<List<Meal>> getAllMeal(String user);
    @Query("SELECT * FROM meal_table")
    Flowable<List<Meal>> getMealsForUser();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Meal meal);
    @Delete
    public void delete(Meal meal);
}
