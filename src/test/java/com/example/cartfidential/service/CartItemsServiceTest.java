package com.example.cartfidential.service;

import com.example.cartfidential.blockchain.singleton.BlockchainSingleton;
import com.example.cartfidential.cart.exception.CartAlreadyDeletedException;
import com.example.cartfidential.cart.exception.CartAlreadyExistsException;
import com.example.cartfidential.cart.exception.CartNotFoundException;
import com.example.cartfidential.cart.model.CartItem;
import com.example.cartfidential.cart.model.CartItems;
import com.example.cartfidential.cart.service.CartService;
import com.example.cartfidential.cart.service.impl.CartServiceImpl;
import com.example.cartfidential.fixture.CartItemFixture;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemsServiceTest {

    private static CartService cartService;
    private static CartItems cart;
    private static final UUID cartId = UUID.randomUUID();

    @BeforeAll
    public static void setup() throws CartAlreadyExistsException {
        cartService = new CartServiceImpl();
        cart = new CartItems();

        ArrayList<CartItem> items = new ArrayList<>();

        items.add(CartItemFixture.buildCartItem());

        cart.setCartId(cartId);
        cart.setItems(items);
        cartService.createCart(cartId, cart);
    }

    @Test
    @Order(1)
    void shouldFindCartById() throws CartNotFoundException {
        CartItems existentCart = cartService.findCartById(cartId);
        assertNotNull(existentCart);
        assertEquals(1, existentCart.getItems().size());
        assertEquals(2, BlockchainSingleton.getInstance().getSize());

    }

    @Test
    @Order(2)
    void shouldThrowExceptionOnFindCartById() {
        assertThrows(CartNotFoundException.class, () -> cartService.findCartById(UUID.randomUUID()));
    }

    @Test
    @Order(3)
    void shouldCreateCart() throws CartAlreadyExistsException {
        CartItems existentCart = cartService.createCart(UUID.randomUUID(), cart);
        assertNotNull(existentCart);
        assertEquals(1, existentCart.getItems().size());
        assertEquals(3, BlockchainSingleton.getInstance().getSize());
    }

    @Test
    @Order(4)
    void shouldThrowExceptionOnCartCreation() {
        assertThrows(CartAlreadyExistsException.class, () -> cartService.createCart(cartId, cart));
    }

    @Test
    @Order(5)
    void shouldUpdateCart() throws CartNotFoundException {
        ArrayList<CartItem> items = cart.getItems();
        items.add(CartItemFixture.buildCartItem());
        cart.setItems(items);

        CartItems existentCart = cartService.updateCart(cartId, cart);
        assertNotNull(existentCart);
        assertEquals(2, existentCart.getItems().size());
        assertEquals(4, BlockchainSingleton.getInstance().getSize());
    }

    @Test
    @Order(6)
    void shouldThrowExceptionOnCartUpdate() {
        assertThrows(CartNotFoundException.class, () -> cartService.updateCart(UUID.randomUUID(), cart));
    }

    @Test
    @Order(7)
    void shouldDeleteCart() throws CartAlreadyDeletedException, CartNotFoundException {
        cartService.deleteCart(cartId);
        assertEquals(cartId, BlockchainSingleton.getInstance().getLastCart(cartId).getCartId());
        assertNull(BlockchainSingleton.getInstance().getLastCart(cartId).getItems());
        assertEquals(5, BlockchainSingleton.getInstance().getSize());
    }

    @Test
    @Order(8)
    void shouldThrowCartAlreadyDeletedExceptionOnCartDeletion() {
        assertThrows(CartAlreadyDeletedException.class, () -> cartService.deleteCart(cartId));
    }

    @Test
    @Order(9)
    void shouldThrowNotFoundExceptionOnCartDeletion() {
        assertThrows(CartNotFoundException.class, () -> cartService.deleteCart(UUID.randomUUID()));
    }

}
