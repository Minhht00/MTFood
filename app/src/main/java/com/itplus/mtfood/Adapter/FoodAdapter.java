package com.itplus.mtfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itplus.mtfood.Activity.ShowDetailActivity;
import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.Model.Food;
import com.itplus.mtfood.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;

    public FoodAdapter(@NonNull Context context, @NonNull List<Food> food) {
        this.context = context;
        this.foodList = food;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new FoodAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food = foodList.get(position);
//        holder.id.setText(category.getId());
//        holder.img.setText(category.getImg());
        holder.name.setText(food.getName());
        holder.fee.setText(String.valueOf(food.getFee()));
        String picUrl = food.getPic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", foodList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, fee;
        ImageView pic;
        TextView btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }


}
