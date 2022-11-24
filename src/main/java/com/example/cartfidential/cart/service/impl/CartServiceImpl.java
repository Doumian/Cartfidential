package com.example.cartfidential.cart.service.impl;

import com.example.cartfidential.blockchain.model.Block;
import com.example.cartfidential.blockchain.singleton.BlockchainSingleton;
import com.example.cartfidential.cart.exception.CartAlreadyDeletedException;
import com.example.cartfidential.cart.exception.CartAlreadyExistsException;
import com.example.cartfidential.cart.exception.CartNotFoundException;
import com.example.cartfidential.cart.model.CartItems;
import com.example.cartfidential.cart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final BlockchainSingleton blockchainInstance = BlockchainSingleton.getInstance();

    @Override
    public CartItems findCartById(UUID cartId) throws CartNotFoundException {
        return getExistentCart(cartId);
    }

    @Override
    public CartItems createCart(UUID cartId, CartItems cartItems) throws CartAlreadyExistsException {
        CartItems existentCart = blockchainInstance.getLastCart(cartId);
        if (existentCart != null) throw new CartAlreadyExistsException(cartId);
        CartItems newCart = new CartItems();
        newCart.setCartId(cartId);
        newCart.setItems(cartItems.getItems());
        addNewBlockToBlockchain(newCart);
        return cartItems;
    }

    @Override
    public CartItems updateCart(UUID cartId, CartItems cart) throws CartNotFoundException {
        getExistentCart(cartId);

        CartItems newCart = new CartItems();
        newCart.setCartId(cartId);
        newCart.setItems(cart.getItems());

        addNewBlockToBlockchain(newCart);

        return getExistentCart(cartId);
    }

    @Override
    public void deleteCart(UUID cartId) throws CartAlreadyDeletedException, CartNotFoundException {
        CartItems existentCart = blockchainInstance.getLastCart(cartId);

        checkIfCartExists(cartId, existentCart);
        checkIfIsAlreadyDeleted(cartId, existentCart);

        CartItems cartItems = new CartItems();
        cartItems.setCartId(cartId);
        cartItems.setItems(null);
        addNewBlockToBlockchain(cartItems);
    }

    private static void checkIfCartExists(UUID cartId, CartItems existentCart) throws CartNotFoundException {
        if (existentCart == null) throw new CartNotFoundException(cartId);
    }

    private static void checkIfIsAlreadyDeleted(UUID cartId, CartItems existentCart) throws CartAlreadyDeletedException {
        if (existentCart.getCartId() != null && existentCart.getItems() == null)
            throw new CartAlreadyDeletedException(cartId);
    }

    private CartItems getExistentCart(UUID cartId) throws CartNotFoundException {
        CartItems existentCart = blockchainInstance.getLastCart(cartId);
        checkIfCartExists(cartId, existentCart);
        return existentCart;
    }

    private void addNewBlockToBlockchain(CartItems cartItems) {
        Block newBlock = new Block(blockchainInstance.getLastHash(), cartItems);
        blockchainInstance.addBlock(newBlock);
    }
}
