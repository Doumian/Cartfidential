package com.example.cartfidential.cart.exception;

import java.util.UUID;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(UUID cartId) {
        super("Cart with id: " + cartId + " not found");
    }
}