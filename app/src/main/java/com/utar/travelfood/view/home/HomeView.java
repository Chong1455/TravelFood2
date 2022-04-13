package com.utar.travelfood.view.home;

import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setCategory(List<Categories.Category> category);
    void setCountry(List<Meals.Meal> country);
    void onErrorLoading(String message);
}
