package com.stickshift.letscode;

public class BUY2GET1Offer implements CartItemOffer {
    @Override
    public String getOfferName() {
        return "BUY2GET1";
    }

    @Override
    public int getDiscountedValue(CartItem cartItem) {
        int quantityBeforeOffer = cartItem.getQuantityBeforeOffer();
        return (quantityBeforeOffer - quantityBeforeOffer/3)*cartItem.getProduct().getUnitPrice();
    }


    @Override
    public int getAdjustedQuantity(CartItem cartItem) {
        // if quantity is greater than 3, return the quantity based on offer
        // if quantity mod 3 is 2 return quantity + 1
        int quantityBeforeOffer = cartItem.getQuantityBeforeOffer();
        return quantityBeforeOffer + (quantityBeforeOffer % 3 == 2 ? 1 : 0);
    }
}
