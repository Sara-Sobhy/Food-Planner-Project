package com.example.foodapp.model;

public class FoodCategory {
    String strCategory;
    String strCategoryThumb;
    String strCategoryDescription;

    public FoodCategory(String strCategory, String strCategoryThumb, String getStrCategoryDescription) {
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = getStrCategoryDescription;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String getStrCategoryDescription) {
        this.strCategoryDescription = getStrCategoryDescription;
    }
}
