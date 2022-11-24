package com.example.cartfidential.blockchain.service.impl;

import com.example.cartfidential.blockchain.model.Block;
import com.example.cartfidential.blockchain.service.BlockchainService;
import com.example.cartfidential.blockchain.singleton.BlockchainSingleton;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService {
    private final BlockchainSingleton blockchainInstance = BlockchainSingleton.getInstance();

    @Override
    public boolean replace(List<Block> newBlockchain) {
        return blockchainInstance.replace(newBlockchain);
    }

    @Override
    public boolean isValid(List<Block> newBlockchain) {
        return blockchainInstance.isValid(newBlockchain);
    }
}
