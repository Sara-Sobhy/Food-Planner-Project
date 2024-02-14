package com.example.foodapp.modules.home.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;

import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder> {
    List<FoodCountryResponse.FoodCountry> countries;
    Context context;
    public CountriesRecyclerAdapter(List<FoodCountryResponse.FoodCountry> countries,Context context)
    {
        this.countries=countries;
        this.context=context;
    }

    @NonNull
    @Override
    public CountriesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.countries_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesRecyclerAdapter.ViewHolder holder, int position) {
        FoodCountryResponse.FoodCountry foodCategory=countries.get(position);
        holder.tvCountry.setText(countries.get(position).getStrArea());

        holder.Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController((Activity) context,R.id.fragmentContainerView);
                Bundle bundle=new Bundle();
                bundle.putString("areaMeals",foodCategory.getStrArea());
                Log.d("TAG", "onClick: areaaaaaaaaaaaa"+foodCategory.getStrArea());
                navController.navigate(R.id.action_homeFragment_to_listAllMeals,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry;
        ConstraintLayout Area;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry=itemView.findViewById(R.id.tvCountry);
            Area=itemView.findViewById(R.id.AreaLayout);
        }
    }
}
