package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.food1, "Berger1", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food2, "Berger2", "9", "Jung Food"));
        list.add(new MainModel(R.drawable.food3, "Berger3", "8", "Jung Food"));
        list.add(new MainModel(R.drawable.food4, "Berger4", "2", "Jung Food"));
        list.add(new MainModel(R.drawable.food5, "Berger5", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food6, "Berger6", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food7, "Berger7", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food8, "Berger8", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food9, "Berger9", "10", "Jung Food"));
        list.add(new MainModel(R.drawable.food10, "Berger10", "10", "Jung Food"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.mainRV.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.mainRV.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}