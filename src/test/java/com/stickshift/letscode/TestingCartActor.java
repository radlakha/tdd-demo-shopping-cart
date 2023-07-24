package com.stickshift.letscode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.ClassRule;
import org.junit.Test;

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;

public class TestingCartActor {
@ClassRule

// instantiate the actor system
public static final TestKitJunitResource testKit = new TestKitJunitResource();


@Test
public void testAddItem() {
    Cart mockCart = mock(Cart.class);
    // instantiate the Akka actor
    ActorRef<CartActor.Command> underTest = testKit.spawn(CartActor.create(mockCart), "cartactor");
    
    // send a message to the actor
    underTest.tell(new CartActor.AddItem(new Product("Dove Soap", 50), 3, "BUY2GET1"));
    
    // assert that the actor has responded
    verify(mockCart, times(1))
        .AddItem(new Product("Dove Soap", 50), 3, "BUY2GET1");
}

@Test
public void testGetValueInvocation() {
    
    TestProbe<CartActor.Value> replyTo = testKit.createTestProbe(CartActor.Value.class);
    Cart mockCart = mock(Cart.class);
    // instantiate the Akka actor
    ActorRef<CartActor.Command> underTest = testKit.spawn(CartActor.create(mockCart), "cartactor");

    // send a message to the actor
    underTest.tell(new CartActor.GetValue(replyTo.getRef()));
    
    // assert that the actor has responded
    verify(mockCart, times(1))
        .getValue();
}  

@Test
public void testGetValue() {
    
    TestProbe<CartActor.Value> replyTo = testKit.createTestProbe(CartActor.Value.class);
    // instantiate the Akka actor
    ActorRef<CartActor.Command> underTest = testKit.spawn(CartActor.create(new Cart()), "cartactor");

    // send a message to the actor
    underTest.tell(new CartActor.AddItem(new Product("Dove Soap", 50), 3, "BUY2GET1"));

    // send a message to the actor
    underTest.tell(new CartActor.GetValue(replyTo.getRef()));
    
    replyTo.expectMessage(new CartActor.Value(100.0)); 
}  
}