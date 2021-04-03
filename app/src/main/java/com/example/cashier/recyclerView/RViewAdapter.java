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
import com.example.cashier.state_manager.StateManager;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.RViewHolder> {

    private TextView totalPriceRef;

    public RViewAdapter(TextView totalPriceRef) {
        this.totalPriceRef = totalPriceRef;

        updateTotalPrice();
    }

    @Override
    public int getItemCount() {
        return StateManager.getProductsInBasket().size();
    }

    public void removeItemAt(int position) {
        StateManager.getProductsInBasket().remove(position);
        updateTotalPrice();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, StateManager.getProductsInBasket().size());
    }

    public void addItem(ProductModel productModel) {
        StateManager.addProduct(productModel);
        notifyItemInserted(StateManager.getProductsInBasket().size() - 1);
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < StateManager.getProductsInBasket().size(); i++) {
            double price = StateManager.getProductsInBasket().get(i).getUnitPrice() * StateManager.getProductsInBasket().get(i).getNumberOfProducts();
            totalPrice += price;
        }
        totalPriceRef.setText(String.format("%.2f€", totalPrice));
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
        ProductModel product = StateManager.getProductsInBasket().get(position);
        holder.textProductName.setText(product.getName());
        holder.textProductCode.setText(product.getCode());
        holder.textProductPrice.setText(String.format("%.2f€", product.getUnitPrice()));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =  StateManager.getProductsInBasket().get(position).getNumberOfProducts();
                holder.textQuantity.setText(Integer.toString(quantity + 1));
                StateManager.getProductsInBasket().get(position).setNumberOfProducts(quantity + 1);
                updateTotalPrice();
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =  StateManager.getProductsInBasket().get(position).getNumberOfProducts();
                if (quantity > 0) {
                    holder.textQuantity.setText(Integer.toString(quantity - 1));
                    StateManager.getProductsInBasket().get(position).setNumberOfProducts(quantity - 1);
                    updateTotalPrice();
                }
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