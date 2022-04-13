package com.utar.travelfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.utar.travelfood.R;
import com.utar.travelfood.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCountry extends RecyclerView.Adapter<RecyclerViewMealByCountry.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewMealByCountry(Context context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
<<<<<<< HEAD
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
=======
    public RecyclerViewMealByCountry.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_food, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
=======
    public void onBindViewHolder(@NonNull RecyclerViewMealByCountry.RecyclerViewHolder viewHolder, int i) {
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d

        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);

        String strMealName = meals.get(i).getStrMeal();
        viewHolder.mealName.setText(strMealName);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
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
        RecyclerViewMealByCountry.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
