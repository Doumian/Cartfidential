package com.example.cartfidential.cart.service;

import com.example.cartfidential.cart.exception.CartAlreadyDeletedException;
import com.example.cartfidential.cart.exception.CartAlreadyExistsException;
import com.example.cartfidential.cart.exception.CartNotFoundException;
import com.example.cartfidential.cart.model.CartItems;

import java.util.UUID;

public interface CartService {
    CartItems findCartById(UUID cartId) throws CartNotFoundException;

    CartItems createCart(UUID cartId, CartItems cart) throws CartAlreadyExistsException;

    CartItems updateCart(UUID cartId, CartItems cart) throws CartNotFoundException;

    void deleteCart(UUID cartId) throws CartAlreadyDeletedException, CartNotFoundException;

}
