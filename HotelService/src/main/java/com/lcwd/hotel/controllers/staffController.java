package com.lcwd.hotel.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class staffController {
	
	//jub multiple urls hai to hamko api GateWay se multiple urls ko acces karne ke lia do like...
	
	@GetMapping
	public ResponseEntity<List<String>> getStaffs(){
		
		List<String> list = Arrays.asList("Virendra","Akchata","Upasana","Yadav");
		
		return new ResponseEntity<List<String>>(list,HttpStatus.OK);
		
	}

}
