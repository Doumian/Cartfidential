package com.example.cartfidential.blockchain.model;

import com.example.cartfidential.cart.model.CartItems;
import com.example.cartfidential.utils.StringUtils;
import lombok.Data;

import java.util.Date;

@Data
public class Block {

    private long timeStamp;
    private String lastHash;
    private CartItems cartItems;
    private String hash;

    private static int nonce;
    private static final int ZEROES_PREFIX = 4;

    public Block(String lastHash, CartItems cartItems) {
        this.lastHash = lastHash;
        this.cartItems = cartItems;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(lastHash, timeStamp, cartItems);
    }

    public static Block getGenesisBlock() {
        Block genesisBlock = new Block("0", new CartItems());
        generateHashFromBlock(genesisBlock);
        return genesisBlock;
    }

    public static void generateHashFromBlock(Block block) {
        String prefixString = new String(new char[ZEROES_PREFIX]).replace('\0', '0');
        while (!block.getHash().substring(0, ZEROES_PREFIX).equals(prefixString)) {
            ++nonce;
            block.setHash(calculateHash(block.lastHash, block.timeStamp, block.cartItems));
        }
    }

    public static String calculateHash(String lastHash, long timeStamp, CartItems cart) {
        return StringUtils.applySha256(
                lastHash +
                        timeStamp +
                        nonce +
                        cart
        );
    }


}
