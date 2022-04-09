package com.utar.travelfood.view.country;

import com.utar.travelfood.model.Meals;

import java.util.List;

public interface CountryView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
