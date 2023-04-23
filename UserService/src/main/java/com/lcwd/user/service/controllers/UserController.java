package com.lcwd.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	int retryCount=1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBraker",fallbackMethod = "ratingHotelFallback")//this is for sercutBraker condition by-->Rajelience4j Library used for imolement this
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")//es method ke throgh ham retry karte hai again by again Esko implement karna acha rahta hai.. upar wale mwthod se
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSengleUser(@PathVariable String userId){
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	//create fallBack method for circuit Braker
	
	
	public ResponseEntity<User> ratingHotelFallback(String UserId,Exception ex){
		
//	  logger.info("fallback is executed because service is down...",ex.getMessage());
		
		
	  User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created becaus the Service is Down").userId("1123456").build();
	  return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}

}
