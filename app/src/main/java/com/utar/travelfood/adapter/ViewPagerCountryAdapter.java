package com.utar.travelfood.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.utar.travelfood.model.Meals;
import com.utar.travelfood.view.country.CountryFragment;

import java.util.List;

public class ViewPagerCountryAdapter extends FragmentPagerAdapter {

    private List<Meals.Meal> countries;

    public ViewPagerCountryAdapter(FragmentManager fm, List<Meals.Meal> countries) {
        super(fm);
        this.countries = countries;
    }

    @Override
    public Fragment getItem(int i) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", countries.get(i).getStrArea());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return countries.get(position).getStrArea();
    }
}
