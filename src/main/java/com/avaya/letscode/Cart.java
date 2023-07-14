package com.avaya.letscode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    //List of items in the cart with respective unit quantity
    private final Map<Product, CartItem> items;
    
    //List of offers applicable to the cart
    // Map<String, Offer> cartOffers;
    private final List<CartOffer> offers;

    int quantity = 0;       //Total number of items in cart

    Cart(){
        this.items = new HashMap<>();
        this.offers = new ArrayList<>();
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public void AddItem(Product product) {
        this.AddItem(product, 1);
    }

    public void AddItem(Product product, int quantity) {
        this.AddItem(product, quantity,"");
    }

    public void AddItem(Product product, int quantity, String offer) {
        this.quantity += quantity;
        CartItem cartItem = new CartItem(product, quantity, Offer.create(offer));
        this.items.merge(product, cartItem, CartItem::merge);
    }

    public void AddOffer(String offer) {
        this.offers.add((CartOffer)Offer.create(offer));
    }

    public int getQuantity() {
        // iterate through the items and get the total quantity
        return this.items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public int getQuantityBeforeOffer() {
        return quantity;
    }


    public int getQuantity(Product product) {
        return this.items.containsKey(product) ? this.items.get(product).getQuantity() : 0;
    }

    public double getValue() {

        //stream offer list and apply each offer to the cart and find the minimum value
        // if cart has no offers, return the cart value before offer
        return this.offers.isEmpty() ? this.getValueBeforeOffer() : this.offers.stream().mapToDouble(offer -> offer.getDiscountedValue(this)).min().getAsDouble();
        
    }

    public double getValueBeforeOffer() {
        return this.items.values().stream().mapToInt(CartItem::getValue).sum();
    }
}
