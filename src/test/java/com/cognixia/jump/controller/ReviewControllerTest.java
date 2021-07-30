package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.responsemodels.ReviewDetails;
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
	private ReviewService service;
	
	@InjectMocks
	private ReviewController controller;
	
	@Test
	void testGetAllReviews() throws Exception {
		
		String uri = STARTING_URI + "reviews";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2012");
		
		ReviewDetails review = new ReviewDetails();
		
		List<ReviewDetails> reviewDetails = new ArrayList<ReviewDetails>();
		
		review.setUser(new User(1, "kianoosh", "Mokhtari", "123", "ADMIN", null));
		review.setReview(new Review(2, 4, "it's AMAZING", d , new Restaurant(), new User()));
		review.setRestaurant(new Restaurant(1, "McDonalds", "123 St", "Junk Food", new ArrayList<Review>()));
		
		reviewDetails.add(review);

		when( service.getReviews() ).thenReturn( reviewDetails );
		
		MvcResult result = mockMvc.perform(get(uri)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andReturn();
//			.andExpect(content()
//			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$[0]").value(1));
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
//				.andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$[0]").value(1));
		
		String content = result.getResponse().getContentAsString();
		
		System.out.println(content);
		System.out.println(reviewDetails);
		
	}


}






