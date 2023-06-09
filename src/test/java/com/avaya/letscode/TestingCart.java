package com.avaya.letscode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestingCart {
    Cart testCart;
    @Before
    public void setup(){
        testCart = new Cart();
    }
    @Test
    public void testCartInitialization ()
    {
        Assert.assertTrue(testCart.isEmpty());
    }
    @Test
    public void testAddToCart(){
        testCart.AddItem(new Product("Dove Soap", 50));
        Assert.assertEquals(1, testCart.getQuantity());
        Assert.assertEquals(50, testCart.getValue(), 0.0);
    }
    @Test
    public void testCartInUseIsNotEmpty(){
        testCart.AddItem(new Product("Dove Soap", 50));
        Assert.assertFalse(testCart.isEmpty());
    }
    @Test
    public void testAddMoreThanOneQuanity(){
        testCart.AddItem(new Product("Dove Soap", 50), 3);
        Assert.assertEquals(3, testCart.getQuantity());
        Assert.assertEquals(150, testCart.getValue(), 0.0);
    }
    @Test
    public void testAddMultipleProducts(){
        Product doveSoap = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoap, 3);
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 2);
        Assert.assertEquals(3,testCart.getQuantity(doveSoap));
        Assert.assertEquals(2,testCart.getQuantity(patanjaliSoap));
    }
    @Test
    public void testMultipleCallstoAddProduct(){
        Product doveSoapOne = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoapOne, 3);
        Product doveSoapTwo = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoapTwo, 1);
        Assert.assertEquals(4,testCart.getQuantity(doveSoapOne));
    }
    @Test
    public void testAddOfferToCart(){
        Product doveSoap = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoap, 3, "BUY2GET1");
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 2);
        Assert.assertEquals(3,testCart.getQuantity(doveSoap));
        Assert.assertEquals(2,testCart.getQuantity(patanjaliSoap));
        Assert.assertEquals(150, testCart.getValue(), 0.0);
    }
    @Test
    public void testAddMultipleOfferToCart(){
        Product doveSoap = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoap, 3, "BUY2GET1");
        testCart.AddItem(doveSoap, 2, "BUY2GET1");
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 2);
        Assert.assertEquals(5,testCart.getQuantity(doveSoap));
        Assert.assertEquals(2,testCart.getQuantity(patanjaliSoap));
        Assert.assertEquals(250, testCart.getValue(), 0.0);
    }
    @Test
    public void testAddProductsWithDifferingUnitPriceToCart(){
        Product doveSoapNew = new Product("Dove Soap", 50);
        Product doveSoapOld = new Product("Dove Soap", 40);
        testCart.AddItem(doveSoapNew, 3, "BUY2GET1");
        testCart.AddItem(doveSoapOld, 2, "BUY2GET1");
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 2);
        Assert.assertEquals(3,testCart.getQuantity(doveSoapNew));
        Assert.assertEquals(2,testCart.getQuantity(doveSoapOld));
        Assert.assertEquals(2,testCart.getQuantity(patanjaliSoap));
        Assert.assertEquals(230, testCart.getValue(), 0.0);
    }
    @Test
    public void testAddProductsWithFirstNOnDiscountOffer(){
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 5,"FIRST2OFF50");
        Assert.assertEquals(5,testCart.getQuantity(patanjaliSoap));
        Assert.assertEquals(100, testCart.getValue(), 0.0);
    }
    @Test
    // This test is to check offers designed for the discount on total cart value
    public void testAddProductsWithCartValueDiscountOffer(){
        Product doveSoap = new Product("Dove Soap", 50);
        testCart.AddItem(doveSoap, 10, "BUY2GET1");
        Product patanjaliSoap = new Product("Patanjali Soap", 25);
        testCart.AddItem(patanjaliSoap, 10,"FIRST2OFF50");
        Product HerbalShampoo = new Product("Herbal Shampoo", 250);
        testCart.AddItem(HerbalShampoo, 2);
        testCart.AddOffer("CART5OFF");
        Assert.assertEquals(10,testCart.getQuantity(doveSoap));
        Assert.assertEquals(10,testCart.getQuantity(patanjaliSoap));
        Assert.assertEquals(2,testCart.getQuantity(HerbalShampoo));
        Assert.assertEquals(1021.25, testCart.getValue(),0);
    }

    @After
    public void teardown(){
        testCart = null;
    }
}

