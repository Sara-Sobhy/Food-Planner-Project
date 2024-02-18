package com.example.foodapp.modules.plane.view;

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
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.favorite.view.FavoriteAdapter;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
    Context context;
    List<Plane> planeList;
    PlanInterface planInterface;

    public PlanAdapter(Context context, List<Plane> planeList, PlanInterface planInterface) {
        this.context = context;
        this.planeList = planeList;
        this.planInterface = planInterface;
    }

    @NonNull
    @Override
    public PlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_plan,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.MyViewHolder holder, int position) {

        Log.d("TAG", "onBindViewHolderiiiiiiiiiiiiiii: " + planeList.get(position).getStrMeal());
        holder.textOne.setText(planeList.get(position).getStrMeal());
        holder.textTwo.setText((CharSequence) planeList.get(position).getDate());
        Glide.with(context)
                .load(planeList.get(position).getStrMealThumb())
                .into(holder.img);

        holder.btnDeleteFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planInterface.removeMealFromPlan(planeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return planeList.size();
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
