package com.effe.dsimilar.service;

import java.util.List;

import com.effe.dsimilar.exception.ProductException;
import com.effe.dsimilar.model.Rating;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;

	public List<Rating> getProductsRating(Long productId);
}
