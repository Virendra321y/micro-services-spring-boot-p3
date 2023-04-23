package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
//this is called Declerative aproch which is use to acll apis fromdiffrent servive class 	
	//get
	
	//post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	//put
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	
	//delete
	@DeleteMapping("/rating/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);

}
