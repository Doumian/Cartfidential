package com.example.cartfidential.cart.exception;

import java.util.UUID;

public class CartAlreadyDeletedException extends Exception {
    public CartAlreadyDeletedException(UUID cartId) {
        super("Cart with id: " + cartId + " is already deleted");
    }
}