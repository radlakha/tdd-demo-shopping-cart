package com.stickshift.letscode;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class CartActor extends AbstractBehavior<CartActor.AddItem> {
    private final Cart cart;

    public static class AddItem {
        public final Product product;
        public final int quantity;
        public final String offer;

        public AddItem(Product product, int quantity, String offer) {
            this.product = product;
            this.quantity = quantity;
            this.offer = offer;
        }
    }

    public CartActor(ActorContext<AddItem> context, Cart cart) {
        super(context);
        this.cart = cart;
    }

    public static Behavior<AddItem> create(Cart cart) {
        return Behaviors.setup(context -> new CartActor(context, cart));
    }

    @Override
    public Receive<AddItem> createReceive() {
        return newReceiveBuilder().onMessage(AddItem.class, this::onAddItem).build();
    }

    private Behavior<AddItem> onAddItem(AddItem message) {
        cart.AddItem(message.product, message.quantity, message.offer);
        return this;
    }

}
