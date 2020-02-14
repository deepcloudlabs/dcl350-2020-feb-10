package com.example.binance.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.binance.dto.Ticker;

@Service
public class BinanceRestConsumer {

	@Scheduled(fixedRate = 5_000)
	public void call() {
		RestTemplate rt = new RestTemplate();
		Ticker ticker = rt.getForEntity("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT", Ticker.class)
				.getBody();
		System.out.println(ticker);
	}

}
