package com.avaya.letscode;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    //List of items in the cart with respective unit quantity
    Map<Product, CartItem> items;       
    
    //List of offers applicable to the cart
    Map<String, Offer> cartOffers;


    int quantity = 0;       //Total number of items in cart
    public boolean isEmpty() {
        return quantity == 0;
    }

    Cart(){
        this.items = new HashMap<>();
        this.cartOffers = new HashMap<>();
    }
    public void AddItem(Product product) {
        this.AddItem(product, 1);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        // if cart has no offer, return the sum of all items in the cart
        if (this.cartOffers.isEmpty()) {
            return this.items.values().stream().mapToInt(CartItem::getValue).sum();
        }
        else {
            double discount = 0;
            for (Map.Entry<String, Offer> entry : this.cartOffers.entrySet()) {
                discount += entry.getValue().getDiscount(this);
            }
            double cartValue = this.items.values().stream().mapToInt(CartItem::getValue).sum();
            return cartValue - cartValue*discount/100;
        }
    }

    public void AddItem(Product product, int quantity) {
        this.AddItem(product, quantity,"");
    }

    public int getQuantity(Product product) {
        return this.items.containsKey(product) ? this.items.get(product).getQuantity() : 0;
    }

    public void AddItem(Product product, int quantity, String offer) {
        this.quantity += quantity;

        CartItem cartItem = new CartItem(product, quantity, Offer.create(offer));
        this.items.merge(product, cartItem, CartItem::merge);
    }

    public void AddOffer(String offer) {
        this.cartOffers.put(offer, Offer.create(offer));
    }
}
