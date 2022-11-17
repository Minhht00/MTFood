package com.itplus.mtfood.Remote;

import com.itplus.mtfood.Model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FoodService {
    @GET("food/get-all-food")
    Call<List<Food>> getAllFood();

    @POST("food/insert/")
    Call<List<Food>> addUser(@Body Food food);
//
//    @PUT("user/update/{id}")
//    Call<User> updateUser(@Path("id") int id, @Body User user);
//
//    @DELETE("user/delete/{id}")
//    Call<User> deleteUser(@Path("id") int id);
}
