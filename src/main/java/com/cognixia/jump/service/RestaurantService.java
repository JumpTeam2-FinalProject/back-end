package com.cognixia.jump.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.repository.RestaurantRepository;
import com.cognixia.jump.model.Restaurant;

@Service
public class RestaurantService {
	
	private final RestaurantRepository restaurantRepository;
	
	
	public RestaurantService(RestaurantRepository repo) {
		this.restaurantRepository = repo;
	}
	
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.status(200).body(restaurantList);
    }

    public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant) {
        return ResponseEntity.status(201).body(restaurantRepository.save(restaurant));
    }

    public ResponseEntity<Restaurant> updateRestaurant(int id, Restaurant updatedRestaurant)  {
        Restaurant currentRestaurant = restaurantRepository.findById(id).orElseThrow(
        		() -> new IllegalStateException("Restaurant with id = " + id + " does not exist"));
        
        	currentRestaurant.setAddress(updatedRestaurant.getAddress());
        	currentRestaurant.setDescription(updatedRestaurant.getDescription());
        	currentRestaurant.setName(updatedRestaurant.getName());
        	
	
        return ResponseEntity.status(200).body(restaurantRepository.save(currentRestaurant));
    }
    
    public ResponseEntity<Restaurant> getSingleRestaurantById(int id){
    	 Restaurant singleRestaurant = restaurantRepository.findById(id).orElseThrow(
         		() -> new IllegalStateException("Restaurant with id = " + id + " does not exist"));
    	
    	return ResponseEntity.status(200).body(singleRestaurant);
    }

}
