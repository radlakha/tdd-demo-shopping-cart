package com.avaya.letscode;

public class CART5OFFOffer extends Offer {
    public String getOfferName() {
        return "CART5OFF";
    }

    public double getDiscount(Cart cart) {
        // passsing the cart is to help offer to calculate the discount based on cart value and items in the cart
        // do not use cart.getValue() here as it will cause infinite loop

        // if cart value is greater than 1000, return 5% discount
        double cartValue = cart.items.values().stream().mapToInt(CartItem::getValue).sum();
        return (cartValue >= 1000) ? 5 : 0;
    }
}
