package com.example.cartfidential.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(setterPrefix = "set")
public class CartItem {

    @NotNull
    @JsonProperty(value = "id")
    private UUID id;

    @NotNull
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @JsonProperty(value = "quantity")
    private Integer quantity;

    @NotNull
    @JsonProperty(value = "price")
    private Double price;

}
