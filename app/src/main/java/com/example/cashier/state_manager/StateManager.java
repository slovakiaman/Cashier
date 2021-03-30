package com.example.cashier.state_manager;

import java.util.ArrayList;

public class StateManager {
    /*
        Reference to state singleton object
     */
    public static State stateRef = null;

    public static void InitState(){
        if (stateRef == null){
            stateRef = new State();
        }
    }

    /*
        Get products in basket
     */
    public static ArrayList<ProductModel> getProductsInBasket(){
        stateRef.productsInBasket = createTestProducts();
        return stateRef.productsInBasket;
    }

    private static ArrayList<ProductModel> createTestProducts() {
        ArrayList<ProductModel> items = new ArrayList<>();
        items.add(new ProductModel(1, "Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new ProductModel(2, "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel(3, "Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new ProductModel(4, "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel(5, "Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new ProductModel(6, "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel(7, "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel(8, "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel(9, "Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new ProductModel(10, "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel(11, "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel(12, "Horčica", "HBGN144", 1, 3.15f));
        return items;
    }

    /*
        Get merchant
     */
    public static MerchantModel getMerchant(){
        return stateRef.merchant;
    }

    private static class State{

        /*
            Products actually in basket
        */
        private ArrayList<ProductModel> productsInBasket;

        /*
            Merchant who uses this cashier
        */
        private MerchantModel merchant;

        public State(){
            this.productsInBasket = new ArrayList<>();
            this.merchant = null;
        }
    }
}


