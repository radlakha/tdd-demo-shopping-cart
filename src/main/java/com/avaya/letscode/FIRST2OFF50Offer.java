package com.avaya.letscode;

public class FIRST2OFF50Offer implements Offer {
    @Override
    public String getOfferName() {
        return "FIRST2OFF50";
    }
    
    @Override
    public int getDiscountedValue(CartItem cartItem) {
        return (cartItem.getQuantityBeforeOffer() > 1) ? (cartItem.getQuantityBeforeOffer()-1)*cartItem.getProduct().getUnitPrice() : cartItem.getQuantityBeforeOffer()*cartItem.getProduct().getUnitPrice();
    }


}
