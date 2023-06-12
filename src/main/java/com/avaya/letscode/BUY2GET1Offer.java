package com.avaya.letscode;

public class BUY2GET1Offer extends Offer {
    public String getOfferName() {
        return "BUY2GET1";
    }

    public int getItemTotalWithOffer(Product product, int quantity) {
        return (quantity - quantity/3)*product.getUnitPrice();
    }
}
