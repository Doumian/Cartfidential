package com.example.cartfidential.cart.controller;

import com.example.cartfidential.cart.exception.CartAlreadyDeletedException;
import com.example.cartfidential.cart.exception.CartAlreadyExistsException;
import com.example.cartfidential.cart.exception.CartNotFoundException;
import com.example.cartfidential.cart.model.CartItems;
import com.example.cartfidential.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/cartfidential/v1")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<CartItems> getCartById(@PathVariable(value = "id") UUID cartId) throws CartNotFoundException {
        CartItems cart = cartService.findCartById(cartId);
        return ResponseEntity.ok().body(cart);
    }

    @PostMapping("/carts/{id}")
    public CartItems createCart(@PathVariable(value = "id") UUID cartId, @Validated @RequestBody CartItems cartItems) throws CartAlreadyExistsException {
        return cartService.createCart(cartId, cartItems);
    }

    @PatchMapping("/carts/{id}")
    public ResponseEntity<CartItems> updateCart(@PathVariable(value = "id") UUID cartId,
                                                @Validated @RequestBody CartItems cart) throws CartNotFoundException {
        CartItems cartItems = cartService.updateCart(cartId, cart);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity deleteCart(@PathVariable(value = "id") UUID cartId) throws CartAlreadyDeletedException, CartNotFoundException {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }

}
