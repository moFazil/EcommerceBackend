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
import com.effe.dsimilar.model.Review;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.request.ReviewRequest;
import com.effe.dsimilar.service.ReviewService;
import com.effe.dsimilar.service.UserService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {

		User user = userService.findUserProfileByJwt(jwt);

		Review review = reviewService.createReview(req, user);

		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId)
			throws UserException, ProductException {

		List<Review> review = reviewService.getAllReview(productId);

		return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
	}
}
