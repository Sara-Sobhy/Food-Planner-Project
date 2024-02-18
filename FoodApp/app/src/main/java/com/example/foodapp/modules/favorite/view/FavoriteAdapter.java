package com.example.foodapp.modules.favorite.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>{
    List<Meal> mealList;
    Context context;
    IFavourite iFavourite;

    public FavoriteAdapter(List<Meal> mealList, Context context, IFavourite iFavourite) {
        this.mealList = mealList;
        this.context = context;
        this.iFavourite = iFavourite;
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_fav,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.MyViewHolder holder, int position) {

            Log.d("TAG", "onBindViewHolderiiiiiiiiiiiiiii: " + mealList.get(position).getStrMeal());
            holder.textOne.setText(mealList.get(position).getStrMeal());
            holder.textTwo.setText(mealList.get(position).getStrArea());
            Glide.with(context)
                    .load(mealList.get(position).getStrMealThumb())
                    .into(holder.img);

            holder.btnDeleteFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iFavourite.removeMeal(mealList.get(position));
                }
            });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textOne;
        TextView textTwo;
        Button btnDeleteFav;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.imageView);
            textOne=itemView.findViewById(R.id.tvOne);
            textTwo=itemView.findViewById(R.id.textView5);
            btnDeleteFav=itemView.findViewById(R.id.btnDeleteFav);
        }
    }
}
