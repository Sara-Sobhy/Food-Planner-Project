package com.example.foodapp.model;

import java.util.ArrayList;

public class FoodCountryResponse {
    ArrayList<FoodCountry> meals;

    public FoodCountryResponse(ArrayList<FoodCountry> meals) {
        this.meals = meals;
    }

    public ArrayList<FoodCountry> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<FoodCountry> meals) {
        this.meals = meals;
    }

    public class FoodCountry{
        String strArea;

        public FoodCountry(String strArea) {
            this.strArea = strArea;
        }

        public String getStrArea() {
            return strArea;
        }

        public void setStrArea(String strArea) {
            this.strArea = strArea;
        }
    }
}
