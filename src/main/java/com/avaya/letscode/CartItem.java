package com.avaya.letscode;

public class CartItem {
    private final Product product;
    private int quantity;
    private final String offer;

    public CartItem(Product product, int quantity, String offer) {
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
        
        if (this.offer.equals("BUY2GET1")) {
            return (this.quantity - this.quantity/3)*product.getUnitPrice();
        } else if (this.offer.equals("FIRST2OFF50")) {
            return (this.quantity > 1) ? (this.quantity-1)*this.product.getUnitPrice() : this.quantity*this.product.getUnitPrice();
        }

        return this.product.getUnitPrice()*this.getQuantity();
    }
}
