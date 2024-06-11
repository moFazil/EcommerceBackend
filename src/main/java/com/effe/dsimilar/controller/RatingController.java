package com.effe.dsimilar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effe.dsimilar.exception.ProductException;
import com.effe.dsimilar.exception.UserException;
import com.effe.dsimilar.model.Rating;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.request.RatingRequest;
import com.effe.dsimilar.service.RatingService;
import com.effe.dsimilar.service.UserService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating>createRating(@RequestBody RatingRequest req ,
			@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Rating rating = ratingService.createRating(req, user);
		
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Rating> ratings = ratingService.getProductsRating(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.CREATED);
	}
}
