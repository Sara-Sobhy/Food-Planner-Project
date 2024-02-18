package com.example.foodapp.modules.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.foodapp.R;
import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.model.QueryResult;
import com.example.foodapp.modules.plane.presenter.PlanPresenter;
import com.example.foodapp.modules.search.presenter.SearchPresenter;
import com.example.foodapp.network.AppRemoteDataSource;
import com.google.android.material.search.SearchBar;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class searchFragment extends Fragment implements SearchInterface{

    RecyclerView recyclerView;
    SearchPresenter searchPresenter;
    String query;
    SearchView searchBar;
    SearchAdapter searchAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.RCSearch);
        searchBar=view.findViewById(R.id.searchBar);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchPresenter = new SearchPresenter(this);
        search();
        return view;
    }
    public void search()
    {
        /* ahmed
        * ahmed
        * ahmed khaled
        *
        *  */
        Observable<String> queryObservable= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        emitter.onNext(newText);
                        return false;
                    }
                });
            }
        }).debounce(200, TimeUnit.MILLISECONDS);
        queryObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                searchPresenter.getSearch(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void showResult(List<QueryResult> stringList) {
        searchAdapter=new SearchAdapter(stringList);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }
}