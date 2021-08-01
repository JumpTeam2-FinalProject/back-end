package com.cognixia.jump.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.RestaurantRepository;
import com.cognixia.jump.service.MyUserDetails;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.RestaurantService;
import com.cognixia.jump.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)

class RestaurantControllerTest {
	
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
	private RestaurantRepository restaurantRepository;
	
	@MockBean
	private RestaurantService service;
	
	@InjectMocks 
	private RestaurantController controller;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void testCreateNewRestaurant() throws Exception {
		
		String uri = STARTING_URI + "restaurant";
		
		List<Review> reviews = new ArrayList<Review>();
		
		Restaurant restaurant = new Restaurant(2, "Pizza Hut", "098 St", "Large pizza chain", "Italian", reviews);
			
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Restaurant> resEntity = new ResponseEntity<>(
				restaurant,
				header,
				HttpStatus.CREATED
				);
		
		when(service.addRestaurant(Mockito.any())).thenReturn(resEntity);
		
		mockMvc.perform(post(uri)
			.content(asJsonString(new Restaurant(2, "Pizza Hut", "098 St", "Large pizza chain", "Italian", reviews)))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))	
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.restaurant_id").value(2))
			.andExpect(jsonPath("$.text").value("Pizza Hut"));
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
