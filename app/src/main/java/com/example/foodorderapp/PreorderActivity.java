package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityPreorderBinding;

public class PreorderActivity extends AppCompatActivity {

    ActivityPreorderBinding preorderBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preorderBinding=ActivityPreorderBinding.inflate(getLayoutInflater());
        setContentView(preorderBinding.getRoot());
        DBHelper dbHelper = new DBHelper(this);
        if(getIntent().getIntExtra("type",1)==1) {

            final int image = getIntent().getIntExtra("image", 0);
            final String name = getIntent().getStringExtra("name");
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String description = getIntent().getStringExtra("description");

            preorderBinding.PFoodname.setText(name);
            preorderBinding.PFoodimage.setImageResource(image);
            preorderBinding.PTotalprice.setText(String.format("%d", price));
            preorderBinding.PDescription.setText(description);

            preorderBinding.posB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = Integer.parseInt(preorderBinding.itemCount.getText().toString());
                    temp += 1;
                    preorderBinding.itemCount.setText(Integer.toString(temp));
                    preorderBinding.PTotalprice.setText(Integer.toString(temp * price));
                }
            });

            preorderBinding.negB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = Integer.parseInt(preorderBinding.itemCount.getText().toString());
                    if (temp > 1) {
                        temp -= 1;
                    }
                    preorderBinding.itemCount.setText(Integer.toString(temp));
                    preorderBinding.PTotalprice.setText(Integer.toString(temp * price));
                }
            });


            preorderBinding.POrderbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = dbHelper.insertOrder(
                            preorderBinding.editTextTextPersonName.getText().toString(),
                            preorderBinding.editTextPhone.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(preorderBinding.itemCount.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Cursor cursor=dbHelper.getOrderById(getIntent().getIntExtra("id",0));

            preorderBinding.PFoodname.setText(cursor.getString(7));
            preorderBinding.PFoodimage.setImageResource(cursor.getInt(4));
            preorderBinding.PTotalprice.setText(String.format("%d", cursor.getInt(3)*cursor.getInt(5)));
            preorderBinding.PDescription.setText(cursor.getString(6));
            preorderBinding.itemCount.setText(String.format("%d", cursor.getInt(5)));
            preorderBinding.editTextTextPersonName.setText(cursor.getString(1));
            preorderBinding.editTextPhone.setText(cursor.getString(2));

            int price=cursor.getInt(3);

            preorderBinding.posB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = Integer.parseInt(preorderBinding.itemCount.getText().toString());
                    temp += 1;
                    preorderBinding.itemCount.setText(Integer.toString(temp));
                    preorderBinding.PTotalprice.setText(Integer.toString(temp * price));
                }
            });
            preorderBinding.negB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = Integer.parseInt(preorderBinding.itemCount.getText().toString());
                    if (temp > 1) {
                        temp -= 1;
                    }
                    preorderBinding.itemCount.setText(Integer.toString(temp));
                    preorderBinding.PTotalprice.setText(Integer.toString(temp * price));
                }
            });
            preorderBinding.POrderbutton.setText("UPDATE NOW");
            preorderBinding.POrderbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated =dbHelper.updateOrder(
                            preorderBinding.editTextTextPersonName.getText().toString(),
                            preorderBinding.editTextPhone.getText().toString(),
                            Integer.parseInt(preorderBinding.itemCount.getText().toString()),
                            getIntent().getIntExtra("id", 0)
                    );
                    if(isUpdated){
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            cursor.close();

        }

    }
}