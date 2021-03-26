package com.example.cashier.recyclerView;

import java.util.ArrayList;

public class FakeDb {

    private ArrayList<Item> items;

    public FakeDb() {
        items = new ArrayList<>();
        items.add(new Item("Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new Item("Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new Item("Kofola 0.5l", "KSGF001", 1, 0.69f));
        items.add(new Item("Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new Item("Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new Item("Horčica", "HBGN144", 1, 3.15f));
        items.add(new Item("Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new Item("Horčica", "HBGN144", 1, 3.15f));
        items.add(new Item("Yogobela Jahody", "YHGN045", 1, 0.50f));
        items.add(new Item("Horčica", "HBGN144", 1, 3.15f));
        items.add(new Item("Kukurica 100g", "KHGF005", 1, 1.80f));
        items.add(new Item("Horčica", "HBGN144", 1, 3.15f));
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
