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
        // if item has no offer, return the quantity, else return the quantity based on offer
        return (this.offer == null) ? this.quantity : ((CartItemOffer) this.offer).getAdjustedQuantity(this);
    }

    private int getValueBeforeOffer() {
        return this.product.getUnitPrice() * this.quantity;
    }

    public int getValue() {
        // if item has no offer, return the product unit price * quantity, else return the item total based on offer
        return (this.offer != null) ? ((CartItemOffer) this.offer).getDiscountedValue(this) : getValueBeforeOffer();
    }

    public int getQuantityBeforeOffer() {
        return this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }
    }

}
