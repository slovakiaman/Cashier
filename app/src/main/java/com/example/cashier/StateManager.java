package com.example.cashier;

import android.util.Log;

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

    public static void AddScannedCode(String newCode){
        if (stateRef != null){
            stateRef._scannedCodes.add(newCode);
        }
    }

    public static void RemoveScannedCode(String newCode){
        if (stateRef != null){
            for (int i = 0; i < stateRef._scannedCodes.size(); i++){
                if (newCode.equals(stateRef._scannedCodes.get(i))){
                    stateRef._scannedCodes.remove(i);
                    return;
                    // TODO add to each barcode ID because you may scan same barcode
                }
            }
        }
    }

    public static String GetScannedCode(String code){
        for (String str : stateRef._scannedCodes){
            if (code.equals(str)){
                return str;
                // TODO add to each barcode ID because you may scan same barcode
            }
        }
        return null;
    }

    private static class State{

        private ArrayList<String> _scannedCodes = new ArrayList<String>();

        public State(){
            for (int i=0; i<20; i++){
                _scannedCodes.add(i + " test");
            }
        }
    }
}


