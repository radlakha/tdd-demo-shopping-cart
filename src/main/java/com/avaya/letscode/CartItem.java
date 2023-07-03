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
        // if item has no offer, return the quantity
        if (this.offer == null) {
            return this.quantity;
        }

        // if item has offer, return the quantity based on offer
        return (this.offer).getAdjustedQuantity(this.product, this.quantity);
    }

    public int getCartItemValue() {
        
        // if item has no offer, return the product unit price * quantity
        if (this.offer == null) {
            return this.product.getUnitPrice()*this.getQuantity();
        }
        
        // if item has offer, return the item total based on offer
        return (this.offer).getDiscountedValue(this.product, this.quantity);

    }

}
