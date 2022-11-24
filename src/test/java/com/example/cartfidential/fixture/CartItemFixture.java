package com.example.cartfidential.fixture;

import com.example.cartfidential.cart.model.CartItem;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CartItemFixture {

    static List<String> cartItems = Arrays.asList("Apples", "Beers", "Carrots", "Donuts");
    static Random random = new Random();

    private CartItemFixture() {
    }

    public static CartItem buildCartItem() {
        return CartItem
                .builder()
                .setId(UUID.randomUUID())
                .setName(cartItems.get(random.nextInt(cartItems.size())))
                .setQuantity(random.nextInt(100))
                .setPrice(random.nextDouble(100))
                .build();
    }


}
