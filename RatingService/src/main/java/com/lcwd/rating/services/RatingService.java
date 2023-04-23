package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {
	
	//create
	Rating create(Rating rating);
	
	//get all rating
	List<Rating> getRatings();
	
	//get all by userid
	List<Rating> getRatingbyUserId(String userid);
	
	//get all byy hotel
	List<Rating> getRatingByHotelId(String hotelId);
	

}
