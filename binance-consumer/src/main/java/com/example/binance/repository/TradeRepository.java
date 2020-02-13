package com.example.binance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.binance.entity.Trade;

public interface TradeRepository 
             extends MongoRepository<Trade,String>{

}
