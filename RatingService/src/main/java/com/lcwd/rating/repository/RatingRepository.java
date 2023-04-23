package com.lcwd.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lcwd.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	
	//create custom method which we need in service class
	//ye method ko create karne ka kuch tarika hota hai ->'model class me->'UserId. Esko cammle case me laga de
	List<Rating> findByUserId(String userId);
	
	//model class me variable ko cammle case me laga dege-> HotelId
	List<Rating> findByHotelId(String hotelId);
	

}
