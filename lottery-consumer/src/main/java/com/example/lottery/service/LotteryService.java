package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="lottery",
          fallback = DummyLotteryService.class)
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	List<Integer> rakamlarGetir();
}