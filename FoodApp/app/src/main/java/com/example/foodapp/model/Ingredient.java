package com.example.foodapp.model;

public class Ingredient {

        String idIngredient;
        String strIngredient;
        String strDescription;
        public Ingredient()
        {

        }

        public Ingredient(String idIngredient, String strIngredient, String strDescription) {
            this.idIngredient = idIngredient;
            this.strIngredient = strIngredient;
            this.strDescription = strDescription;
        }

        public String getIdIngredient() {
            return idIngredient;
        }

        public void setIdIngredient(String idIngredient) {
            this.idIngredient = idIngredient;
        }

        public String getStrIngredient() {
            return strIngredient;
        }

        public void setStrIngredient(String strIngredient) {
            this.strIngredient = strIngredient;
        }

        public String getStrDescription() {
            return strDescription;
        }

        public void setStrDescription(String strDescription) {
            this.strDescription = strDescription;
        }
    }

