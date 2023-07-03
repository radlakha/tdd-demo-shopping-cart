package com.avaya.letscode;

public class BUY2GET1Offer implements Offer {
    @Override
    public String getOfferName() {
        return "BUY2GET1";
    }

    @Override
    public int getDiscountedValue(Product product, int quantity) {
        return (quantity - quantity/3)*product.getUnitPrice();
    }


    @Override
    public int getAdjustedQuantity(Product product, int quantity) {
        // if quantity is greater than 3, return the quantity based on offer
        // if quantity mod 3 is 2 return quantity + 1
        return quantity + (quantity % 3 == 2 ? 1 : 0);
    }
}
