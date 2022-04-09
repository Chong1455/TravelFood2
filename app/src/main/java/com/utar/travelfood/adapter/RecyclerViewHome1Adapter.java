package com.utar.travelfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utar.travelfood.R;
import com.utar.travelfood.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHome1Adapter extends RecyclerView.Adapter<RecyclerViewHome1Adapter.RecyclerViewHolder> {

    private List<Meals.Meal> countries;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHome1Adapter(List<Meals.Meal> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHome1Adapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_country,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHome1Adapter.RecyclerViewHolder viewHolder, int i) {

        String strCountryName = countries.get(i).getStrArea();
        viewHolder.countryName.setText(strCountryName);
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.countryName)
        TextView countryName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHome1Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
