package com.example.foodapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.sql.Date;

@Entity(tableName = "Plane_table",primaryKeys = {"idMeal","userEmail","date"})
public class Plane {
    @NonNull
    String idMeal;
    //emailuser
    @NonNull
    String userEmail;
    String strMeal;
    String strMealThumb;
    @NonNull
    Date date;

    public Plane(@NonNull String idMeal, @NonNull String userEmail, String strMeal, String strMealThumb, @NonNull Date date) {
        this.idMeal = idMeal;
        this.userEmail = userEmail;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.date = date;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }
}
