package com.example.foodapp.modules.details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.ListOfMeals.view.MealsNameRecycleerAdapter;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHandler> {
    List<Meal> mealList;
    Context context;

    public IngredientsAdapter(List<Meal> mealList, Context context) {
        this.mealList = mealList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_ingredient,parent,false);
        return new ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHandler holder, int position) {
        holder.tvCategory.setText(mealList.get(position).getStrIngredient1());
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        TextView tvCategory;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            tvCategory=itemView.findViewById(R.id.ingredient);


        }
    }
}
