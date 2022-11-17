package com.itplus.mtfood.Remote;

import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("category/get-all-cate")
    Call<List<Category>> getAllCategory();

    @POST("user/insert/")
    Call<User> addUser(@Body User user);

    @PUT("user/update/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("user/delete/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
