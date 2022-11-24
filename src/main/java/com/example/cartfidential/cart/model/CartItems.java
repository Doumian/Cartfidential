package com.example.cartfidential.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;


@Data
public class CartItems {

    @JsonProperty(value = "cartId")
    private UUID cartId;

    @JsonProperty(value = "items")
    @Valid
    private ArrayList<CartItem> items;

}
