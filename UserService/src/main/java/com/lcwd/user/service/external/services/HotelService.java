package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {//FeienClient hamko calling method ka implementation ko likhne ki jarurat nahi hai this the benfete of feaneClient
	
//this is called declerative Aproch which is provide feignClient in Interface to implemnt
	@GetMapping("/hotels/{hotelId}")//Eska implemantation banane ki jarurat nahi hai feignClint hone pe
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
