package com.utar.travelfood.view.country;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.utar.travelfood.R;
import com.utar.travelfood.adapter.ViewPagerCountryAdapter;
import com.utar.travelfood.model.Meals;
import com.utar.travelfood.view.home.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();

    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Meals.Meal> countries =
                (List<Meals.Meal>) intent.getSerializableExtra(HomeActivity.EXTRA_COUNTRY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);

        ViewPagerCountryAdapter adapter = new ViewPagerCountryAdapter(
                getSupportFragmentManager(),
                countries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
