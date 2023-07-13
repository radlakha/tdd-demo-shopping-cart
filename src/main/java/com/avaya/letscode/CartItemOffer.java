package com.avaya.letscode;

public interface CartItemOffer extends Offer {
    // For item level offers calculate item total based on offer.
    // This function will be implemented in all derived classes
    default int getDiscountedValue(CartItem cartItem) {
        return cartItem.getProduct().getUnitPrice() * cartItem.getQuantityBeforeOffer();
    }


    default int getAdjustedQuantity(CartItem cartItem) {
        return cartItem.getQuantityBeforeOffer();
    }
}
