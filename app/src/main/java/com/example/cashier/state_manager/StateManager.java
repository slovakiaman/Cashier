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
        if (stateRef.productsInBasket.isEmpty())
            stateRef.productsInBasket = createTestProducts();
        return stateRef.productsInBasket;
    }

    public static void addProduct(ProductModel productModel) {
        stateRef.productsInBasket.add(productModel);
    }

    private static ArrayList<ProductModel> createTestProducts() {
        ArrayList<ProductModel> items = new ArrayList<>();
        items.add(new ProductModel("Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new ProductModel("Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel("Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new ProductModel( "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel( "Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new ProductModel( "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel( "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel( "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel( "Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new ProductModel( "Horčica", "HBGN144", 1, 3.15f));
        items.add(new ProductModel( "Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new ProductModel( "Horčica", "HBGN144", 1, 3.15f));
        return items;
    }

    private static MerchantModel createTestMerchant(){
        MerchantModel merchantModel = new MerchantModel("Potraviny Koruna","123456789","Hurbanova","12","Zilina","123 45");
        return merchantModel;
    }

    /*
        Get merchant
     */
    public static MerchantModel getMerchant(){
        stateRef.merchant = createTestMerchant();
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


