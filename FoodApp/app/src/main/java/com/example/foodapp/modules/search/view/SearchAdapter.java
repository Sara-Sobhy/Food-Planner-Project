package com.example.foodapp.modules.search.view;

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

import com.example.foodapp.R;
import com.example.foodapp.model.QueryResult;
import com.example.foodapp.modules.plane.view.PlanAdapter;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    List<QueryResult> queryResultList;

    public SearchAdapter(List<QueryResult> queryResultList)
    {
        this.queryResultList=queryResultList;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolderiiiiiiiiiiiiiii: " + queryResultList.get(position).getResult());
        holder.textOne.setText(queryResultList.get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return queryResultList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textOne;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textOne=itemView.findViewById(R.id.tvOne);

        }
    }
}
