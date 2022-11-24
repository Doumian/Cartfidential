package com.example.cartfidential.blockchain.singleton;

import com.example.cartfidential.blockchain.model.Block;
import com.example.cartfidential.cart.model.CartItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockchainSingleton {

    private static BlockchainSingleton blockChainSingleton;
    private static final List<Block> blockchain = new ArrayList<>();

    private BlockchainSingleton() {
        blockchain.add(Block.getGenesisBlock());
    }

    public static BlockchainSingleton getInstance() {
        if (blockChainSingleton == null)
            blockChainSingleton = new BlockchainSingleton();

        return blockChainSingleton;
    }

    public synchronized void addBlock(Block newBlock) {
        Block.generateHashFromBlock(newBlock);
        blockchain.add(newBlock);
    }

    public boolean replace(List<Block> newBlockchain) {
        return isValid(newBlockchain);
    }

    public boolean isValid(List<Block> newBlockchain) {
        return isBlockchainValid(newBlockchain) && dataIsNotTampered(newBlockchain);
    }

    private boolean isBlockchainValid(List<Block> blockchain) {
        String previousHash = null;
        for (Block block : blockchain) {
            String currentHash = block.getHash();
            if (previousHash != null && !currentHash.equals(previousHash)) {
                return false;
            }
            previousHash = currentHash;
        }
        return true;
    }

    private boolean dataIsNotTampered(List<Block> newBlockchain) {
        for (int i = 0; i <= newBlockchain.size() && i <= blockchain.size(); i++) {
            if (!newBlockchain.get(i).getHash().equals(blockchain.get(i).getHash())) return false;
        }
        return true;
    }

    public CartItems getLastCart(UUID cartId) {
        return blockchain.stream()
                .map(Block::getCartItems)
                .filter(cartItem -> cartId.equals(cartItem.getCartId()))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public String getLastHash() {
        return blockchain.get(blockchain.size() - 1).getHash();
    }

    public int getSize() {
        return blockchain.size();
    }

    public Block getGenesisBlock() {
        return blockchain.get(0);
    }
}
