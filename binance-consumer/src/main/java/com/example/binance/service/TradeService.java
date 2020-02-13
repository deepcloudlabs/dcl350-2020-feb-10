package com.example.binance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.binance.entity.Trade;
import com.example.binance.repository.TradeRepository;

@Service
public class TradeService {
	@Autowired private TradeRepository tradeRepo;
	
	@EventListener
	public void elma(Trade trade) {
		tradeRepo.save(trade);
		System.out.println(trade);
	}
}
