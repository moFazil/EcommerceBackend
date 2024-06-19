package com.effe.dsimilar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.effe.dsimilar.exception.ProductException;
import com.effe.dsimilar.model.Product;
import com.effe.dsimilar.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);

	public String deleteProduct(Long productId) throws ProductException;

	public Product updateProduct(Long productId, Product req) throws ProductException;

	public Product findProductById(Long id) throws ProductException;

	public List<Product> findProductByCategory(String category);

	public List<Product> findAllProducts();

	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String Stock, Integer pageNumber, Integer pageSize);
}
