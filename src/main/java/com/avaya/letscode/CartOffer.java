package com.avaya.letscode;

public interface CartOffer extends Offer {
    // For Cart level offers return discount applicable for the offer. 
    // This function will be implemented in all derived classes for cart level offers
    public default double getDiscountedValue(Cart cart) {
        // passsing the cart is to help offer to calculate the discount based on cart value and items in the cart
        // do not use cart.getValue() here as it will cause infinite loop

        return 0;
    }
}
