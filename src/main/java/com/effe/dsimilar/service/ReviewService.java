package com.effe.dsimilar.service;

import java.util.List;

import com.effe.dsimilar.exception.ProductException;
import com.effe.dsimilar.model.Review;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
