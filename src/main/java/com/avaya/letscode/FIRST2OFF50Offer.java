package com.avaya.letscode;

public class FIRST2OFF50Offer implements Offer {
    @Override
    public String getOfferName() {
        return "FIRST2OFF50";
    }
    
    @Override
    public int getDiscountedValue(Product product, int quantity) {
        return (quantity > 1) ? (quantity-1)*product.getUnitPrice() : quantity*product.getUnitPrice();
    }


}
