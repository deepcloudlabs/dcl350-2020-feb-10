package com.example.lottery.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DummyLotteryService implements LotteryService {

	@Override
	public List<Integer> rakamlarGetir() {
		return Arrays.asList(1, 2, 3, 4, 5, 6);
	}

}
