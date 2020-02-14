package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryConsumerService {
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Autowired
//	private RestTemplate rt;
	@Autowired
	private LotteryService lotterySrv;
//	private List<Server> servers;
//	private AtomicInteger counter = new AtomicInteger();
//	private BaseLoadBalancer loadBalancer;

//	@PostConstruct
//	public void init() {
//		servers = discoveryClient.getInstances("lottery")
//		               .stream()
//		               .map(si -> new Server(si.getHost(), 
//		            		            si.getPort()))
//		               .collect(Collectors.toList());
//		servers.forEach(System.err::println);
//		IRule roundRobinRule = new RoundRobinRule();
//		loadBalancer = LoadBalancerBuilder.newBuilder()
//				         .withRule(roundRobinRule)
//			.buildFixedServerListLoadBalancer(servers);
//	}
	@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
//		int index = counter.getAndIncrement()%servers.size();
//		Server server = servers.get(index );
//		Server server = loadBalancer.chooseServer();
//		String json = rt.getForEntity(
//				 "//lottery/lottery/api/v1/numbers",
//				String.class).getBody();
		System.out.println(lotterySrv.rakamlarGetir());
	}
}
