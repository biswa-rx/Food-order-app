package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.foodorderapp.Adapters.OrderAdapter;
import com.example.foodorderapp.Models.OrderModel;
import com.example.foodorderapp.Models.OrderModel;
import com.example.foodorderapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    OrderAdapter orderAdapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<OrderModel> list;
//        list.add(new OrderModel(R.drawable.food1,"Berger1","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food2,"Berger2","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food3,"Berger3","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food4,"Berger4","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food5,"Berger5","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food6,"Berger6","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food7,"Berger7","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food8,"Berger8","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food9,"Berger9","10","Jung Food"));
//        list.add(new OrderModel(R.drawable.food10,"Berger10","10","Jung Food"));

        dbHelper=new DBHelper(this);
        list=dbHelper.getOrders();


        orderAdapter=new OrderAdapter(list,this);
        binding.orderRV.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.orderRV.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        orderAdapter=new OrderAdapter(dbHelper.getOrders(),this);
        binding.orderRV.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
        super.onResume();
    }
}