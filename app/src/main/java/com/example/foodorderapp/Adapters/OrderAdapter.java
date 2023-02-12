package com.example.foodorderapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DBHelper;
import com.example.foodorderapp.Models.OrderModel;
import com.example.foodorderapp.OrderActivity;
import com.example.foodorderapp.PreorderActivity;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder> {
    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_orderfood, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        OrderModel orderModel = list.get(position);
        holder.imageView.setImageResource(orderModel.getOrderImage());
        holder.foodName.setText(orderModel.getOrderName());
        holder.orderNumber.setText(orderModel.getOrderNumber());
        holder.foodPrice.setText(orderModel.getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PreorderActivity.class);
                intent.putExtra("id", Integer.parseInt(orderModel.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setIcon(R.drawable.delete)
                        .setMessage("Are you want to cancel order")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper dbHelper = new DBHelper(context);
                                if (dbHelper.deleteOrder(orderModel.getOrderNumber()) > 0) {
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }

                                updateData(dbHelper.getOrders());

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).create().show();

                return true;
            }
        });
    }
    public void updateData(ArrayList<OrderModel> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView foodName, orderNumber, foodPrice;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.orderImageView);
            foodName = itemView.findViewById(R.id.orderFoodname);
            orderNumber = itemView.findViewById(R.id.orderNo);
            foodPrice = itemView.findViewById(R.id.OFoodPrice);
        }
    }
}
