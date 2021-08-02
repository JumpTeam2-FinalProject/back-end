package com.cognixia.jump.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetails;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.ReviewService;
import com.cognixia.jump.util.JwtUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)

class ReviewControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api/";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	MyUserDetails userDetails;
	
	@MockBean
	MyUserDetailsService userDetailsService;

	@MockBean 
	JwtUtil jwtUtil;
	
	@MockBean
	private ReviewRepository reviewRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private ReviewService service;
	
	@InjectMocks
	private ReviewController controller;
	
	@Test
	void testGetAllReviews() throws Exception {
		
		String uri = STARTING_URI + "reviews_simple";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2012");
		
		Review review = new Review(2, 4, "it's AMAZING", d, new Restaurant(), new User());
		
		List<Review> reviews = new ArrayList<Review>();
		
		review.setUser(new User(1, "kianoosh", "Mokhtari", "123", "ADMIN", null));
		review.setRestaurant(new Restaurant(1, "McDonalds", "123 St", "Junk Food", "Fast Food", new ArrayList<Review>()));
		
		reviews.add(review);

		when( service.getReviewsSimple() ).thenReturn( reviews );
		
		mockMvc.perform(get(uri)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].review_id").value(2))
			.andExpect(jsonPath("$[0].rating").value(4))
			.andExpect(jsonPath("$[0].review").value("it's AMAZING"));
	}
	
}





