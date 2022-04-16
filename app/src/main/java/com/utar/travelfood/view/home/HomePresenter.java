package com.utar.travelfood.view.home;

import androidx.annotation.NonNull;

import com.utar.travelfood.Utils;
import com.utar.travelfood.model.Categories;
import com.utar.travelfood.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {
    private HomeView view;
    public HomePresenter(HomeView view) {
        this.view = view;
    }
    void getCategories() {
        view.showLoading();
        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                view.hideLoading(); //hide loading when received response
                if (response.isSuccessful() && response.body() != null) {
                    view.setCategory(response.body().getCategories());
                }
                else { view.onErrorLoading(response.message()); }
            }
            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
