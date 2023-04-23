package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	//eske ander ham kaisa v bean declear kar sakte hai pure es project me jo v jarurat hoga uske lia i can
	
	@Bean
	@LoadBalanced//jub ham host and port ke sthan pe ham service ke name ko use karte tab hamko logebalance karna padta hai bean class me,jub multipal instances or serve aak sath runing karti hai tab hamko lodeBalancer ka jarurat padta hai
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
