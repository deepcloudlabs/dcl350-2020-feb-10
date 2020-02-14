package com.example.binance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.binance.entity.Trade;

@Service
public class TradeKafkaProducer {
	@Autowired
	private KafkaTemplate<String, Trade> kf;

	@EventListener
	public void listenTrade(Trade trade) {
		kf.send("trades", trade.getSymbol(), trade);
	}
}
