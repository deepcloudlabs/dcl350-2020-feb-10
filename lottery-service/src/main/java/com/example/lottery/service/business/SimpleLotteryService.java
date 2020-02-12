package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
public class SimpleLotteryService implements LotteryService {
	@Value("${lottery.min}") private int min;
	@Value("${lottery.max}") private int max;
	@Value("${lottery.size}") private int size;
	@Override
	public List<Integer> draw() {
		return ThreadLocalRandom.current()
				   .ints(min,max)
				   .distinct()
				   .limit(size)
				   .sorted()
				   .boxed()
				   .collect(Collectors.toList());
	}

}
