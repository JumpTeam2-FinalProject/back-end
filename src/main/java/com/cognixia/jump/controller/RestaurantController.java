package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.service.RestaurantService;

@RequestMapping("/api")
@RestController
public class RestaurantController {
	
	private final RestaurantService restaurantService;
	
	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	
	@GetMapping(path = "/restaurant")
	public ResponseEntity <List<Restaurant>> getRestaurants(){
		return restaurantService.getAllRestaurants();
	}
	
	@PostMapping(path = "/restaurant")
	public ResponseEntity <Restaurant> postRestaurant(@Valid @RequestBody Restaurant restaurant){
		return restaurantService.addRestaurant(restaurant);
	}
	
	@PutMapping(path = "/restaurant/update/{id}")
	public ResponseEntity<Restaurant> updateRestaurant (@PathVariable int id, @Valid @RequestBody Restaurant restauranUpdated){
		
		return restaurantService.updateRestaurant(id, restauranUpdated);
	}
	
	
	
	

}
