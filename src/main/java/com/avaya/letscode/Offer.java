package com.avaya.letscode;

public class Offer {
    public static Offer create(String offer) {
        if (offer.equals("BUY2GET1"))
                return new BUY2GET1Offer();
        else if (offer.equals("FIRST2OFF50"))
                return new FIRST2OFF50Offer();
        else if (offer.equals("CART5OFF"))
                return new CART5OFFOffer();
        else
        return null;
    } 

    // Return name of the offer. This function will be implemented in all derived classes
    public String getOfferName() {
        return "";
    }

    // Return discount applicable for the offer. This function will be implemented in all derived classes for cart level offers
    public double getDiscount(Cart cart) {
        // passsing the cart is to help offer to calculate the discount based on cart value and items in the cart
        // do not use cart.getValue() here as it will cause infinite loop

        return 0;
    }
}
