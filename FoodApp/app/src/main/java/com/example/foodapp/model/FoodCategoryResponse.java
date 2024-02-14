package com.example.foodapp.model;

import java.util.List;

public class FoodCategoryResponse {
    List<FoodCategory> categories;

    public FoodCategoryResponse(List<FoodCategory> list) {
        this.categories = list;
    }

    public List<FoodCategory> getList() {
        return categories;
    }

    public void setList(List<FoodCategory> list) {
        this.categories = list;
    }
}
