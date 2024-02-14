package com.example.foodapp.modules.ListOfMeals.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.home.view.CategoriesRecyclerAdapter;

import java.util.List;

public class MealsNameRecycleerAdapter extends RecyclerView.Adapter<MealsNameRecycleerAdapter.ViewHandler>{
    List<Meal> mealsList;
    Context context;

    public MealsNameRecycleerAdapter(List<Meal> mealsList, Context context) {
        Log.i("TAG", "MealsNameRecycleerAdapter: ddddddddddddddddddddddd ");
        this.mealsList = mealsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MealsNameRecycleerAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_categoryname,parent,false);
        return new ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsNameRecycleerAdapter.ViewHandler holder, int position) {
        Log.d("TAG", "onBindViewHolder: "+mealsList.get(position).getStrMeal());
        holder.tvCategory.setText(mealsList.get(position).getStrMeal());
        Glide.with(context)
                .load(mealsList.get(position).getStrMealThumb())
                .into(holder.categoryImg);

    }

    @Override
    public int getItemCount() {

        return mealsList.size();
    }
    public class ViewHandler extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView tvCategory;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            categoryImg= itemView.findViewById(R.id.imageView);
            tvCategory=itemView.findViewById(R.id.textView5);

        }
    }
}
