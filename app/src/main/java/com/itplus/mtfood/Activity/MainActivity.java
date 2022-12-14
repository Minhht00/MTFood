package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itplus.mtfood.Adapter.CategoryAdapter;
import com.itplus.mtfood.Adapter.FoodAdapter;
import com.itplus.mtfood.Adapter.UserAdapter;
import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.Model.Food;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.CategoryService;
import com.itplus.mtfood.Remote.FoodService;
import com.itplus.mtfood.Remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txtName;
    User user;
    CategoryService categoryService;
    FoodService foodService;
    List<Category> listCate;
    List<Food> listFood;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryService = APIUtils.getCategoryService();
        foodService = APIUtils.getFoodService();
        Intent data = getIntent();
        String message = data.getStringExtra("email");
        txtName = findViewById(R.id.txtName);
        txtName.setText("Hi " + message);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        Call<List<Category>> call = categoryService.getAllCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                listCate = response.body();
                CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, listCate);
                recyclerViewCategoryList.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });

//        ArrayList<Category> category = new ArrayList<>();
//        category.add(new Category("Pizza","cat_1"));
//        category.add(new Category("Burger","cat_2"));
//        category.add(new Category("Hotdog","cat_3"));
//        category.add(new Category("Drink","cat_4"));
//        category.add(new Category("Donut","cat_5"));
//        category.add(new Category("Donut","cat_5"));
//        adapter = new CategoryAdapter(category);
//        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerViewPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        Call<List<Food>> call = foodService.getAllFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                listFood = response.body();
                FoodAdapter foodAdapter = new FoodAdapter(MainActivity.this, listFood);
                recyclerViewPopularList.setAdapter(foodAdapter);
                foodAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });
    }
}