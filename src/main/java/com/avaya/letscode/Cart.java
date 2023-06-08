package com.avaya.letscode;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Product, CartItem> items;     //List of items in the cart with respective unit quantity

    int quantity = 0;       //Total number of items in cart
    public boolean isEmpty() {
        return quantity == 0;
    }

    Cart(){
        this.items = new HashMap<>();
    }
    public void AddItem(Product product) {
        this.AddItem(product, 1);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getValue() {
        return this.items.values().stream().mapToInt(CartItem::getValue).sum();
    }

    public void AddItem(Product product, int quantity) {
        this.AddItem(product, quantity,"");
    }

    public int getQuantity(Product product) {
        return this.items.containsKey(product) ? this.items.get(product).getQuantity() : 0;
    }

    public void AddItem(Product product, int quantity, String offer) {
        this.quantity += quantity;

        CartItem cartItem = new CartItem(product, quantity, offer);
        this.items.merge(product, cartItem, CartItem::merge);

}
}
