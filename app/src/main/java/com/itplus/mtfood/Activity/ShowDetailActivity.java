package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itplus.mtfood.Model.Food;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.ManagementCart;

import java.util.List;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView btnAddCart;
    private TextView txtTitle, txtFee, txtDescription, txtNumberOrder;
    private ImageView btnPlus, btnMinus, picFood;
    private Food object;
    int numberOrder = 1;
    List<Food> menuList;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBunble();
    }

    private void getBunble() {
        object =  (Food) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);
        txtTitle.setText(object.getName());
        txtFee.setText(object.getFee() + " VNĐ");
        txtDescription.setText(object.getDescription());
        txtNumberOrder.setText(String.valueOf(numberOrder));


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                txtNumberOrder.setText(String.valueOf(numberOrder));
                txtFee.setText(object.getFee()*numberOrder + " VNĐ");

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                txtNumberOrder.setText(String.valueOf(numberOrder));
                txtFee.setText(object.getFee()*numberOrder + " VNĐ");
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }

        });
    }

    private void initView() {
        btnAddCart = findViewById(R.id.btnAddCart);
        txtTitle = findViewById(R.id.txtTitle);
        txtFee = findViewById(R.id.txtFee);
        txtDescription = findViewById(R.id.txtDescription);
        txtNumberOrder = findViewById(R.id.txtNumberOder);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        picFood = findViewById(R.id.picFood);
    }
}