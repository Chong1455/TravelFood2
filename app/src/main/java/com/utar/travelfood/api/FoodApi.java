package com.utar.travelfood.api;

import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("list.php")
    Call<Meals> getCountries(@Query("a") String list);

    @GET("filter.php") 
    Call<Meals> getMealByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<Meals> getMealByCountry(@Query("a") String country);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);
}
