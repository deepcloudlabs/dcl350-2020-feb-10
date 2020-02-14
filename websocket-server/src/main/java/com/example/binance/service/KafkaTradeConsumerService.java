package com.example.binance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.binance.entity.Trade;

@Service
public class KafkaTradeConsumerService {
	@Autowired
	private SimpMessagingTemplate smt;

	@KafkaListener(topics = "trades")
	public void readTrade(Trade trade) {
		System.out.println(trade);
		smt.convertAndSend("/topic/changes", trade);
	}
}
