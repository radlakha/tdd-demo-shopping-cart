package com.stickshift.letscode;

public class FIRST2OFF50Offer implements CartItemOffer {
    @Override
    public String getOfferName() {
        return "FIRST2OFF50";
    }
    
    @Override
    public int getDiscountedValue(CartItem cartItem) {
        int quantityBeforeOffer = cartItem.getQuantityBeforeOffer();
        return (quantityBeforeOffer > 1) ? (quantityBeforeOffer-1)*cartItem.getProduct().getUnitPrice() : quantityBeforeOffer*cartItem.getProduct().getUnitPrice();
    }


}
