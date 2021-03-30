package com.example.cashier.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashier.R;
import com.example.cashier.state_manager.ProductModel;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.RViewHolder> {

    private ArrayList<ProductModel> dataset;

    public RViewAdapter(ArrayList<ProductModel> nDataset) {
        dataset = nDataset;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void removeItemAt(int position) {
        dataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataset.size());
    }

    //---------------------------------------------------------------

    public static class RViewHolder extends RecyclerView.ViewHolder {
        public TextView textProductName;
        public TextView textProductCode;
        public TextView textProductPrice;
        public TextView textQuantity;

        public Button btnPlus;
        public Button btnMinus;
        public Button btnRemove;

        public RViewHolder(View view) {
            super(view);
            this.textProductName = (TextView)view.findViewById(R.id.rvProductName);
            this.textProductCode = (TextView)view.findViewById(R.id.rvProductCode);
            this.textProductPrice = (TextView)view.findViewById(R.id.rvProductPrice);
            this.textQuantity = (TextView)view.findViewById(R.id.rvQuantity);

            this.btnPlus = (Button)view.findViewById(R.id.btnRvPlus);
            this.btnMinus = (Button)view.findViewById(R.id.btnRvMinus);
            this.btnRemove = (Button)view.findViewById(R.id.btnRvRemove);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RViewAdapter.RViewHolder holder, int position) {
        ProductModel product = dataset.get(position);
        holder.textProductName.setText(product.getName());
        holder.textProductCode.setText(product.getCode());
        holder.textProductPrice.setText(String.format("%.2f€", product.getUnitPrice()));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =  Integer.parseInt(holder.textQuantity.getText().toString());
                holder.textQuantity.setText(Integer.toString(quantity + 1));
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =  Integer.parseInt(holder.textQuantity.getText().toString());
                if (quantity > 0)
                    holder.textQuantity.setText(Integer.toString(quantity - 1));
            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemAt(holder.getAdapterPosition());
            }
        });
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        RViewHolder holder = new RViewHolder(view);
        return holder;
    }
}
