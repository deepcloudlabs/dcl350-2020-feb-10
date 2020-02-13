package com.example.lottery.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService {
	
	@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
		RestTemplate rt = new RestTemplate();
		String json = rt.getForEntity(
				"http://localhost:5001/lottery/api/v1/numbers",
				String.class).getBody();
		System.out.println(json);
	}
}
