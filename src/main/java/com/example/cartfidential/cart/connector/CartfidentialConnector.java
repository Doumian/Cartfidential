package com.example.cartfidential.cart.connector;

import com.example.cartfidential.cart.model.CartItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Deprecated
@FeignClient(name="cartfidential", url="https://stoplight.io/mocks/rviewer/cartfidential-api/9400316/")
public interface CartfidentialConnector {

    @GetMapping(value = "/carts/{id}")
    CartItems findCartById(@PathVariable UUID id);

    @PostMapping(value = "/carts/{id}")
    CartItems createCart(@PathVariable UUID id, @Valid @RequestBody CartItems cart);

    @PatchMapping(value = "/carts/{id}")
    CartItems updateCart(@PathVariable UUID id, @Valid @RequestBody CartItems cart);

    @DeleteMapping(value = "/carts/{id}")
    CartItems deleteCart(@PathVariable UUID id);

}
