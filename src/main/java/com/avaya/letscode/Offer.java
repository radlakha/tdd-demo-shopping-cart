package com.avaya.letscode;

public class Offer {
    public static Offer create(String offer) {
        if (offer.equals("BUY2GET1"))
                return new BUY2GET1Offer();
        else if (offer.equals("FIRST2OFF50"))
                return new FIRST2OFF50Offer();
        return null;
    } 
}
