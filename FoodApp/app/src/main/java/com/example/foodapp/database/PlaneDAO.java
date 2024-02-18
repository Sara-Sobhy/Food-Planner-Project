package com.example.foodapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plane;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlaneDAO {
    @Query("SELECT * FROM Plane_table")
    Flowable<List<Plane>> getPlansForUser();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertPlan(Plane meal);
    @Delete
    public void deletePlan(Plane meal);
}
