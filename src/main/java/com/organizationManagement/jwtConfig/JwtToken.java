package com.organizationManagement.jwtConfig;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	long expDate = 30000;
	@Value("${app.key}")
	private String key;

	public String generateJwtToken(Authentication authentication) {

		UserDetails details = (UserDetails) authentication.getPrincipal();

		return Jwts.builder().setSubject(details.getUsername()).setIssuedAt(new Date())
				.claim("userDetails", details)
				.setExpiration(new Date(System.currentTimeMillis() + expDate))
				.signWith(SignatureAlgorithm.HS512, key.getBytes()).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean isTokenExpired(String authToken) {
		return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(authToken).getBody().getExpiration()
				.before(new Date(System.currentTimeMillis()));
	}

	public boolean validateJwtToken(String authToken) {

		Date expiration = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(authToken).getBody()
				.getExpiration();
		return (getUserNameFromToken(authToken) != null && expiration.before(new Date(System.currentTimeMillis())));

	}
}
