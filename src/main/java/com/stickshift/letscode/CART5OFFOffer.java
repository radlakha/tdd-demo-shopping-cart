package com.avaya.letscode;

public class CART5OFFOffer implements CartOffer {
    @Override
    public String getOfferName() {
        return "CART5OFF";
    }

    @Override
    public double getDiscountedValue(Cart cart) {
        // if cart value is greater than 1000, return 5% discount
        double cartValueBeforeOffer = cart.getValueBeforeOffer();
        return (cartValueBeforeOffer >= 1000) ? cartValueBeforeOffer * 0.95 : cartValueBeforeOffer;
    }

}
