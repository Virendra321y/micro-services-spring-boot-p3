package com.lcwd.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	

	@Override
	public User saveUser(User user) {
		
		String randomUserId = UUID.randomUUID().toString();//genrate unick userId
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		
		 User user = userRepository.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User with given id is not found on Server!! :"+userId)));
		 
		 //fetch rating of above user from RATING SERVICE, hamko aak service se dusre service ko call karne
		 //ke lia hamko client chahia...Like rest_template or fien_client
		 
		 
		//http://localhost:8083/ratings/users/9cfbd786-5c49-4a75-903f-196252c04d21   -->it is rating url
		 
		 //Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);   //Dyanmyic id dene ke lia ham id get ->user.getUserId() aise karke
		 
		 Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		 logger.info("{} ",ratingsOfUser);
		 
		 List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		 
		 
		 
		 
		 
		 List<Rating> ratingList = ratings.stream().map(rating ->{
			 
			 //api call to hotel Service to get the hotel
			 //http://localhost:8082/hotels/51e10c49-6b72-4b3c-af47-66537eb1abf0
			 //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
			 
//	,,,,,,,,,,,,		 ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);//host and port ko remove kar ke service ke name se replace kar dege jise koe problem nahi hoga
			 
//			 Hotel hotel = forEntity.getBody();
			 
			 Hotel hotel = hotelService.getHotel(rating.getHotelId());//by using feigenClient implemnt like this.. it follow declerative aproch
			 
//			 logger.info("response status code: {} ",forEntity.getStatusCode());
			 
			 //set the hotel to running 
			 rating.setHotel(hotel);
			 
			 //return the rating
			 return rating;
		 }).collect(Collectors.toList());
		 
		 user.setRatings(ratingList);
		 
		 return user; // http://localhost:8081/users/9cfbd786-5c49-4a75-903f-196252c04d21 ->good output data
	}

}
