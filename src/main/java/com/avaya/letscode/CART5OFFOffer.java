package com.avaya.letscode;

public class CART5OFFOffer implements Offer {
    @Override
    public String getOfferName() {
        return "CART5OFF";
    }

    @Override
    public double getDiscountedValue(Cart cart) {
        // if cart value is greater than 1000, return 5% discount
        return (cart.getCartValueBeforeOffer() >= 1000) ? cart.getCartValueBeforeOffer() * 0.95 : cart.getCartValueBeforeOffer();
    }

}
