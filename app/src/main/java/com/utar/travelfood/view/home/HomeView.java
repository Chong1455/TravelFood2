package com.utar.travelfood.view.home;

import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
<<<<<<< HEAD
    void setMeal(List<Meals.Meal> meal);
=======
>>>>>>> 3ff18495a18847bde44cc26fdbdce0fad998610d
    void setCategory(List<Categories.Category> category);
    void setCountry(List<Meals.Meal> country);
    void onErrorLoading(String message);
}
