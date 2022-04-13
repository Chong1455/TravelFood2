package com.utar.travelfood.adapter;

import android.content.Context;
<<<<<<< HEAD
import android.media.Image;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.RelativeLayout;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utar.travelfood.R;
import com.utar.travelfood.model.Meals;
import com.squareup.picasso.Picasso;
<<<<<<< HEAD
import com.utar.travelfood.view.home.HomeActivity;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewMealByCategory(Context context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewMealByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_food, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealByCategory.RecyclerViewHolder viewHolder, int i) {
<<<<<<< HEAD
        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);
        String strMealName = meals.get(i).getStrMeal();
        viewHolder.mealName.setText(strMealName);

        if (HomeActivity.favouriteFoodArray.size() > 0) {
            // If meal name is in favouriteFoodArray, update the icon
            for (String food : HomeActivity.favouriteFoodArray) {
                if (food.equals(meals.get(i).getStrMeal())) {
                    Picasso.get().load(R.drawable.ic_favorite).placeholder(R.drawable.ic_favorite).into(viewHolder.love);
                }
            }
        }
=======

        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);

        String strMealName = meals.get(i).getStrMeal();
        viewHolder.mealName.setText(strMealName);
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
<<<<<<< HEAD
        @BindView(R.id.love)
        ImageView love;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
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
        RecyclerViewMealByCategory.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
