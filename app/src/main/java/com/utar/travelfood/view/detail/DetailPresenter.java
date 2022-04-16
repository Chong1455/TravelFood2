package com.utar.travelfood.view.detail;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.utar.travelfood.Utils;
import com.utar.travelfood.model.Meals;
import com.utar.travelfood.view.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private DetailView view;
    public DetailPresenter(DetailView view) {
        this.view = view;
    }
    void getMealById(String mealName) {
        view.showLoading();
        Utils.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() !=null && response.body().getMeals() != null){
                            view.setMeal(response.body().getMeals().get(0));
                        }else{
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
