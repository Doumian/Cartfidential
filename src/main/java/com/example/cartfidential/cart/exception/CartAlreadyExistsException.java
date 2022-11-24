package com.example.cartfidential.cart.exception;

import java.util.UUID;

public class CartAlreadyExistsException extends Exception {
    public CartAlreadyExistsException(UUID cartId) {
        super("Cart with id: " + cartId + " already exists");
    }
}