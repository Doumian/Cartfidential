package com.example.cartfidential.blockchain.service;

import com.example.cartfidential.blockchain.model.Block;

import java.util.List;

public interface BlockchainService {
    boolean replace(List<Block> newBlockchain);
    boolean isValid(List<Block> newBlockchain);

}
