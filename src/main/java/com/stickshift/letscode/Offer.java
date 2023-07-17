package com.stickshift.letscode;

public interface Offer {
    public static Offer create(String offer) {
        if (offer.equals("BUY2GET1"))
                return new BUY2GET1Offer();
        else if (offer.equals("FIRST2OFF50"))
                return new FIRST2OFF50Offer();
        else if (offer.equals("CART5OFF"))
                return new CART5OFFOffer();
        else if (offer.equals("CART10OFF"))
                return new CART10OFFOffer();
        else
            return null;
    } 


    // Return name of the offer. This function will be implemented in all derived classes
    default String getOfferName() {
        return "";
    }
}