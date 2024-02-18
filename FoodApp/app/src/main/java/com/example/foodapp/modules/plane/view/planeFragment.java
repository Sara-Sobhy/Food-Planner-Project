package com.example.foodapp.modules.plane.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.favorite.presenter.FavoritePresenter;
import com.example.foodapp.modules.favorite.view.FavoriteAdapter;
import com.example.foodapp.modules.favorite.view.FavoriteFragment;
import com.example.foodapp.modules.plane.presenter.PlanPresenter;
import com.example.foodapp.network.AppRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class planeFragment extends Fragment implements PlanInterface{

    RecyclerView recyclerView;
    PlanPresenter planPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plane, container, false);
        recyclerView = view.findViewById(R.id.RCPlan);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        planPresenter = new PlanPresenter(this, MealRepository.getInstance(LocalDataSource.getInstance(requireContext()), AppRemoteDataSource.getInstance()), requireContext());
        planPresenter.getProducts();
        return view;
    }

    @Override
    public void showMealFromPlan(Flowable<List<Plane>> mealList) {
        String currentUserEmail;
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            currentUserEmail = firebaseUser.getEmail();
        } else {
            currentUserEmail = null;
        }
        Log.d("Plan", "showMeal: aaaaaaaaaaaaa" +currentUserEmail);

        mealList.subscribeOn(Schedulers.io())
                .doOnNext(planes -> Log.d("Plan", "showMeal: "+planes.size()))
                .observeOn(AndroidSchedulers.mainThread())
                .filter(mealItems -> {
                    // Filter the meal items based on the user's email
                    return mealItems.removeIf(mealItem -> !mealItem.getUserEmail().equals(currentUserEmail));
                })

                .subscribe(planes -> {
                    Log.d("Plan", "onChangediiiiii: "+planes);
                    if (planes != null) {
                        Log.d("Plan", "showMealFromPlan: "+planes);
                        PlanAdapter planAdapter = new PlanAdapter(requireContext(),planes, planeFragment.this);
                        recyclerView.setAdapter(planAdapter);
                        planAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void removeMealFromPlan(Plane plane) {
        planPresenter.removeMeal(plane);
    }
}