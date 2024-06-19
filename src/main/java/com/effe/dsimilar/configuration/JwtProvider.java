package com.effe.dsimilar.configuration;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	SecretKey Key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 846000000))
				.claim("email", auth.getName()).signWith(Key).compact();

		return jwt;
	}

	public String getEmailFromToken(String jwt) {
		jwt = jwt.substring(7);

		Claims claims = Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(jwt).getBody();

		String email = String.valueOf(claims.get("email"));

		return email;
	}
}
