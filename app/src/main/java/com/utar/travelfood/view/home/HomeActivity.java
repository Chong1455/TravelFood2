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
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
=======
import android.view.View;
import android.widget.ImageButton;

>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
<<<<<<< HEAD
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
=======

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import com.utar.travelfood.R;
import com.utar.travelfood.Utils;
import com.utar.travelfood.adapter.RecyclerViewHome1Adapter;
import com.utar.travelfood.adapter.RecyclerViewHomeAdapter;
<<<<<<< HEAD
import com.utar.travelfood.adapter.ViewPagerHeaderAdapter;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;
import com.utar.travelfood.view.category.CategoryActivity;
import com.utar.travelfood.view.country.CountryActivity;
import com.utar.travelfood.view.detail.DetailActivity;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
<<<<<<< HEAD
    private ImageButton search;

    public static ArrayList<String> favouriteFoodArray = new ArrayList<>();

    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerMeal;
=======


>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.recyclerCountry)
    RecyclerView recyclerViewCountry;
<<<<<<< HEAD
=======
    @BindView(R.id.imageButton)
    ImageButton search;
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ButterKnife.bind(this);

<<<<<<< HEAD
        // Get firebase user id
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        // Read favourite food from firebase
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child("favouriteFood").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.i("firebase", String.valueOf(task.getResult().getValue()));
                    // Insert data from firebase into array
                    GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                    if (task.getResult().getValue(t) != null) {
                        favouriteFoodArray = task.getResult().getValue(t);
                    }
                }
            }
        });

        search = (ImageButton)findViewById(R.id.imageButton);
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                TextInputLayout textInputLayout = view.findViewById(R.id.search_input);
                Editable searchinput = textInputLayout.getEditText().getText();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL,searchinput.toString());
=======
                TextInputEditText EditText = findViewById(R.id.search_input);
                String searchinput = EditText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, searchinput);
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
                startActivity(intent);
            }
        });

        presenter = new HomePresenter(this);
<<<<<<< HEAD
        presenter.getMeals();
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
        presenter.getCategories();
        presenter.getCountries();
    }

    @Override
    public void showLoading() {
<<<<<<< HEAD
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
<<<<<<< HEAD
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
<<<<<<< HEAD
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
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
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
