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

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceDoesNotExistException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.responsemodels.RestaurantCompleteInfo;
import com.cognixia.jump.service.RestaurantService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
public class RestaurantController {
	
	private final RestaurantService restaurantService;
	
	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@ApiOperation(value= "Get All Restaurant" , 
			notes= "Get all Restaurants with their corresponding informations", 
			response = Restaurant.class)
	@GetMapping(path = "/restaurants")
	public ResponseEntity <List<RestaurantCompleteInfo>> getRestaurants(){
		return restaurantService.getAllRestaurants();
	}
	
	@ApiOperation(value= "Get a Single Restaurant by ID" , 
			notes= "Get a Single Restaurant and all its corresponding reviews by ID ", 
			response = RestaurantCompleteInfo.class)
	@GetMapping(path = "/restaurant/{id}")
	public ResponseEntity<RestaurantCompleteInfo> getSingleRestaurant(@PathVariable int id) throws ResourceDoesNotExistException{
		return ResponseEntity.ok(restaurantService.getSingleRestaurantById(id));
	}
	
	@ApiOperation(value= "Create a single Restaurant" , 
			notes= "Create a single Restaurant with informatino provided in Body", 
			response = Restaurant.class)
	@PostMapping(path = "/restaurant")
	public ResponseEntity <Restaurant> postRestaurant(@Valid @RequestBody Restaurant restaurant) throws ResourceAlreadyExistsException{
		return restaurantService.addRestaurant(restaurant);
	}
	
	@ApiOperation(value= "Update Restaurant by ID" , 
			notes= "Update Restaurant with corresponding information in Body", 
			response = Restaurant.class)
	
	@PutMapping(path = "/restaurant/update/{id}")
	public ResponseEntity<Restaurant> updateRestaurant (@PathVariable int id, @Valid @RequestBody Restaurant restauranUpdated) throws ResourceDoesNotExistException{
		
		return restaurantService.updateRestaurant(id, restauranUpdated);
	}

}
