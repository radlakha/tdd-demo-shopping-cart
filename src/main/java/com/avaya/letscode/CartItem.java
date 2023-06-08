package com.avaya.letscode;

public class CartItem {
    private final Product product;
    private int quantity;
    private final Offer offer;

    public CartItem(Product product, int quantity, Offer offer) {
        this.product = product;
        this.quantity = quantity;
        this.offer = offer;
    }

    public CartItem merge(CartItem cartItem) {
        this.quantity += cartItem.getQuantity();
        return this;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getValue() {
        
        // this function is failing in the test case with null pointer exception
        // this is because the offer is null
        // in case the product is does not have any offer
        // the offer is null
        // hence the null pointer exception
        // to fix this we need to check if the offer is null
        if (this.offer == null) {
            return this.product.getUnitPrice()*this.getQuantity();
        }

        // We are getting assertion error in the test case
        // this is because the offer object is being compared to a string
        // we need to compare the string with the string equivalent of the offer object
        // to fix this lets implement a getOfferName function in the Offer class
        if (this.offer.getOfferName().equals("BUY2GET1")) {
            return (this.quantity - this.quantity/3)*product.getUnitPrice();
        } else if (this.offer.getOfferName().equals("FIRST2OFF50")) {
            return (this.quantity > 1) ? (this.quantity-1)*this.product.getUnitPrice() : this.quantity*this.product.getUnitPrice();
        }
        // if (this.offer.equals("BUY2GET1")) {
        //     return (this.quantity - this.quantity/3)*product.getUnitPrice();
        // } else if (this.offer.equals("FIRST2OFF50")) {
        //     return (this.quantity > 1) ? (this.quantity-1)*this.product.getUnitPrice() : this.quantity*this.product.getUnitPrice();
        // }

        return this.product.getUnitPrice()*this.getQuantity();
    }
}
