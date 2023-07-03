package com.avaya.letscode;

public class CART5OFFOffer implements Offer {
    @Override
    public String getOfferName() {
        return "CART5OFF";
    }

    @Override
    public double getDiscountPercentage(Cart cart) {
        // passsing the cart is to help offer to calculate the discount based on cart value and items in the cart
        // do not use cart.getValue() here as it will cause infinite loop

        // if cart value is greater than 1000, return 5% discount
        double cartValue = cart.items.values().stream().mapToInt(CartItem::getCartItemValue).sum();
        return (cartValue >= 1000) ? 5 : 0;
    }

    @Override
    public int getDiscountedValue(Product product, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiscountedValue'");
    }

    @Override
    public int getAdjustedQuantity(Product product, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjustedQuantity'");
    }
}
