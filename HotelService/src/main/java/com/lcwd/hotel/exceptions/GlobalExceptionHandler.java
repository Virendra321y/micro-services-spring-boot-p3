package com.lcwd.hotel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<Map<String , Object>> notFoundHandler(ResourceNotFoundException ex  ){
		
		Map map = new HashMap<>();
		map.put("message ", ex.getMessage());
		
		map.put("Success",false);
		map.put("status",HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
		
	}

}
