package com.avaya.letscode;

public class CART10OFFOffer implements CartOffer {
    @Override
    public String getOfferName() {
        return "CART10OFF";
    }

    @Override
    public double getDiscountedValue(Cart cart) {
        // if cart value is greater than 2000, return 10% discount
        double cartValueBeforeOffer = cart.getValueBeforeOffer();
        return (cartValueBeforeOffer >= 2000) ? cartValueBeforeOffer * 0.90 : cartValueBeforeOffer;
    }

}