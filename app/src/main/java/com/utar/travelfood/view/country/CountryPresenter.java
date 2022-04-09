package com.utar.travelfood.view.country;


import androidx.annotation.NonNull;

import com.utar.travelfood.Utils;
import com.utar.travelfood.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryPresenter {
    private CountryView view;

    public CountryPresenter(CountryView view) {
        this.view = view;
    }
    
    void getMealByCountry(String country) {
        
        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealByCountry(country);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.setMeals(response.body().getMeals());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
        
    }
}
