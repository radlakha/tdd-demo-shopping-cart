package com.stickshift.letscode;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class CartActor extends AbstractBehavior<CartActor.Command> {
    private final Cart cart;

    // Command interface for all commands to the CartActor
    public static interface Command {
    }

    // All commands to the CartActor
    // AddItem command
    public static class AddItem implements Command {
        public final Product product;
        public final int quantity;
        public final String offer;

        public AddItem(Product product, int quantity, String offer) {
            this.product = product;
            this.quantity = quantity;
            this.offer = offer;
        }
    }

    public static class GetValue implements Command{
        public final ActorRef<Value> replyTo;
        public GetValue(ActorRef<Value> replyTo) {
            this.replyTo = replyTo;
        }
    }
    
    // Wiring up the CartActor to the Cart
    public CartActor(ActorContext<Command> context, Cart cart) {
        super(context);
        this.cart = cart;
    }

    public static Behavior<Command> create(Cart cart) {
        return Behaviors.setup(context -> new CartActor(context, cart));
    }

    // Map the messages to the methods
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
        .onMessage(AddItem.class, this::onAddItem)
        .onMessage(GetValue.class, this::onGetValue)
        .build();
    }

    // Implement the methods to handle the messages
    private Behavior<Command> onAddItem(AddItem message) {
        cart.AddItem(message.product, message.quantity, message.offer);
        return this;
    }

    private Behavior<Command> onGetValue(GetValue message) {
        message.replyTo.tell(new Value(this.cart.getValue()));
        return this;
    }

    // Value class for the response
    // TODO: Why this class is needed?
    public static class Value {
        public final double value;

        public Value(double value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(value);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Value other = (Value) obj;
            if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
                return false;
            return true;
        }
   }
}
