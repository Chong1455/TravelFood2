/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.utar.travelfood.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.textfield.TextInputLayout;
import com.utar.travelfood.R;
import com.utar.travelfood.Utils;
import com.utar.travelfood.adapter.RecyclerViewHome1Adapter;
import com.utar.travelfood.adapter.RecyclerViewHomeAdapter;
import com.utar.travelfood.adapter.ViewPagerHeaderAdapter;
import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;
import com.utar.travelfood.view.category.CategoryActivity;
import com.utar.travelfood.view.country.CountryActivity;
import com.utar.travelfood.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private ImageButton search;

    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerMeal;
    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.recyclerCountry)
    RecyclerView recyclerViewCountry;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ButterKnife.bind(this);

        search = (ImageButton)findViewById(R.id.imageButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout textInputLayout = view.findViewById(R.id.search_input);
                Editable searchinput = textInputLayout.getEditText().getText();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL,searchinput.toString());
                startActivity(intent);
            }
        });

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
        presenter.getCountries();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL,mealName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void setCountry(List<Meals.Meal> country) {
        RecyclerViewHome1Adapter home1Adapter = new RecyclerViewHome1Adapter(country, this);
        recyclerViewCountry.setAdapter(home1Adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewCountry.setLayoutManager(layoutManager);
        recyclerViewCountry.setNestedScrollingEnabled(true);
        home1Adapter.notifyDataSetChanged();

        home1Adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CountryActivity.class);
            intent.putExtra(EXTRA_COUNTRY, (Serializable) country);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

}
