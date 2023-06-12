package com.avaya.letscode;

public class FIRST2OFF50Offer extends Offer {
    public String getOfferName() {
        return "FIRST2OFF50";
    }
    
    public int getItemTotalWithOffer(Product product, int quantity) {
        return (quantity > 1) ? (quantity-1)*product.getUnitPrice() : quantity*product.getUnitPrice();
    }
}
