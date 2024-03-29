package com.cognixia.jump.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// will create new jwts
// pull up info from existing jwts
@Service
public class JwtUtil {
	
	private final String SECRET_KEY = "jump";
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver ) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateTokens(UserDetails userDetails, boolean shouldTokenExpire) {
		
		Map<String, Object> claims = new HashMap<>();
		
		// returns token for user given along with any claims
		return createToken(claims, userDetails.getUsername(), shouldTokenExpire);
	}
	
	private String createToken(Map<String, Object> claims, String subject, boolean shouldExpire) {
		// sets claims
		// subject (person that is being authenticated)
		// set when the token was issued
		// set expiration when token expires and can be no longer used (here its set for 2 hrs)
		// sign it with particular algorithm and secret key that lets you know this token is authentic
		int hoursGood = shouldExpire ? 2 : 9999; // 9,999 hrs ~= 1 yr
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * hoursGood))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return ( username.equals( userDetails.getUsername() ) && !isTokenExpired(token) );
	}
}
