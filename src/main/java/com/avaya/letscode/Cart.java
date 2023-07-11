package com.avaya.letscode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    //List of items in the cart with respective unit quantity
    private Map<Product, CartItem> items;       
    
    //List of offers applicable to the cart
    // Map<String, Offer> cartOffers;
    private List<Offer> cartOffers;


    int quantity = 0;       //Total number of items in cart
    public boolean isEmpty() {
        return quantity == 0;
    }

    Cart(){
        this.items = new HashMap<>();
        this.cartOffers = new ArrayList<>();
    }
    public void AddItem(Product product) {
        this.AddItem(product, 1);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCartValue() {

        //stream offer list and apply each offer to the cart and find the minimum value
        // if cart has no offers, return the cart value before offer
        if (this.cartOffers.isEmpty()) 
            return this.getCartValueBeforeOffer();
        else
            return this.cartOffers.stream().mapToDouble(offer -> offer.getDiscountedValue(this)).min().getAsDouble();
        
    }

    public double getCartValueBeforeOffer() {
        return this.items.values().stream().mapToInt(CartItem::getCartItemValue).sum();
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
        this.cartOffers.add(Offer.create(offer));
    }
}
