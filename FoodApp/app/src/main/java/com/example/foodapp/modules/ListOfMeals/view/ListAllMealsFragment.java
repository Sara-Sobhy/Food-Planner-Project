package com.example.foodapp.modules.ListOfMeals.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.ListOfMeals.presenter.ListOfMealsPresenter;
import com.example.foodapp.modules.home.view.CategoriesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListAllMealsFragment extends Fragment implements ListMealsNameInterface{

    String categoriesName;
    String areaMeals;
    RecyclerView categoryRecyclerName;
    MealsNameRecycleerAdapter mealsNameRecycleerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_list_all_meals, container, false);
        categoryRecyclerName=view.findViewById(R.id.RcNames);
        LinearLayoutManager categoryLayoutManager=new LinearLayoutManager(this.getContext());
        categoryLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerName.setLayoutManager(categoryLayoutManager);

        Log.i("Hi", "onCreateView: hiyaaaaaaaaaa");
        if (getArguments() != null) {

            categoriesName=getArguments().getString("categoryName");
            areaMeals = getArguments().getString("areaMeals");
            if(categoriesName!=null) {
                Log.i("Hi", "onCreateView: afterrr" + categoriesName);
                ListOfMealsPresenter list = new ListOfMealsPresenter(this, categoriesName);
                list.getMealsbyCategory(categoriesName);
                mealsNameRecycleerAdapter = new MealsNameRecycleerAdapter(new ArrayList<>(), this.getContext());
                categoryRecyclerName.setAdapter(mealsNameRecycleerAdapter);
            }
            else if(areaMeals!= null){
                Log.i("Hi", "onCreateView: afterrr" + areaMeals);
                ListOfMealsPresenter list = new ListOfMealsPresenter(this, areaMeals);
                list.getMealsbyarea(areaMeals);

            }
        }



        return view;
    }


    @Override
    public void showCategoriesName(List<Meal> meal) {

        mealsNameRecycleerAdapter= new MealsNameRecycleerAdapter(meal,this.getContext());
        categoryRecyclerName.setAdapter(mealsNameRecycleerAdapter);


    }

    @Override
    public void showCategoriesNameError(Throwable throwable) {

    }

    @Override
    public void showMealByArea(List<Meal> meal) {
        Log.i("TAG", "showMealByArea: from list meal fragment "+meal.size());
        mealsNameRecycleerAdapter = new MealsNameRecycleerAdapter(meal, this.getContext());
        categoryRecyclerName.setAdapter(mealsNameRecycleerAdapter);
    }

    @Override
    public void showMealByAreaError(Throwable throwable) {

    }
}