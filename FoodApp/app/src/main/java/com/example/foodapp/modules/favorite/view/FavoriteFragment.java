package com.example.foodapp.modules.favorite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.modules.favorite.presenter.FavoritePresenter;
import com.example.foodapp.network.AppRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment implements IFavourite{

    RecyclerView recyclerView;
    //FavoriteAdapter favoriteAdapter;
    FavoritePresenter favoritePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.RCFav);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        favoritePresenter = new FavoritePresenter(this, MealRepository.getInstance(LocalDataSource.getInstance(requireContext()), AppRemoteDataSource.getInstance()), requireContext());
        favoritePresenter.getProducts();
        return view;
    }

    @Override
    public void showMeal(Flowable<List<Meal>> mealList) {
        String currentUserEmail;
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            currentUserEmail = firebaseUser.getEmail();
        } else {
            currentUserEmail = null;
        }

        Log.d("TAG", "showMeal: aaaaaaaaaaaaa" +currentUserEmail);

        mealList.subscribeOn(Schedulers.io())
                .doOnNext(meals -> Log.d("TAG", "showMeal: "+meals.size()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    Log.d("TAG", "onChanged: "+meals);
                    meals.removeIf(mealItem -> !mealItem.getUserEmail().equals(currentUserEmail));
                    if (meals != null) {
                        FavoriteAdapter favoriteRecyclerAdapter = new FavoriteAdapter(meals, requireContext(), FavoriteFragment.this);
                        recyclerView.setAdapter(favoriteRecyclerAdapter);
                        favoriteRecyclerAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void removeMeal(Meal meal) {
         favoritePresenter.removeMeal(meal);
    }
}