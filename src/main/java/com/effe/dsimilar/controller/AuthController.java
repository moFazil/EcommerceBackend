package com.effe.dsimilar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effe.dsimilar.configuration.JwtProvider;
import com.effe.dsimilar.exception.UserException;
import com.effe.dsimilar.model.Cart;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.repository.UserRepository;
import com.effe.dsimilar.request.LoginRequest;
import com.effe.dsimilar.response.AuthResponse;
import com.effe.dsimilar.service.CartService;
import com.effe.dsimilar.service.customUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private customUserServiceImplementation customUserService;
	@Autowired
	private CartService cartService;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
			customUserServiceImplementation customUserService, JwtProvider jwtProvider, CartService cartService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.customUserService = customUserService;
		this.jwtProvider = jwtProvider;
		this.cartService = cartService;

	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		System.out.println("Received User Details: " + email + " " + firstName + " " + lastName);

		User isEmailExist = userRepository.findByEmail(email);
		if (isEmailExist != null) {
			throw new UserException("Email Is Already Used With Another Account");
		}

		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);

		System.out.println("Saving User: " + createdUser);

		User savedUser = userRepository.save(createdUser);

		System.out.println("Saved User: " + savedUser);

		Cart cart = cartService.createCart(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");

		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> LoginUserHandler(@RequestBody LoginRequest loginRequest) {

		String userName = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(userName, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("LogIn Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	private Authentication authenticate(String userName, String password) {
		UserDetails userDetails = customUserService.loadUserByUsername(userName);

		if (userName == null) {
			throw new BadCredentialsException("Invalid Username");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
