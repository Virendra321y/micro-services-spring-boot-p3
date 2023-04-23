package com.lcwd.rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.services.RatingService;


@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	

	@Override
	public Rating create(Rating rating) {
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingbyUserId(String userid) {//ye method hamko reposetory nahi dega so hamko create karna padega reposetory me
		
		return ratingRepository.findByUserId(userid);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {//ye method v hamko nahi milega reposetory me create this method
		
		return ratingRepository.findByHotelId(hotelId);
	}

}
