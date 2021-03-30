package com.example.cashier.state_manager;

import com.example.cashier.helpers.HttpHelper;

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
        return stateRef.productsInBasket;
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


