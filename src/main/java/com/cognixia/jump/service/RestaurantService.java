package com.cognixia.jump.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.repository.RestaurantRepository;
import com.cognixia.jump.responsemodels.RestaurantCompleteInfo;
import com.cognixia.jump.util.DataFormatters;
import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceDoesNotExistException;
import com.cognixia.jump.model.Restaurant;

@Service
public class RestaurantService {
	
	private final RestaurantRepository restaurantRepository;
	
	
	public RestaurantService(RestaurantRepository repo) {
		this.restaurantRepository = repo;
	}
	
	public ResponseEntity<List<RestaurantCompleteInfo>> getAllRestaurants() {
        List<RestaurantCompleteInfo> restaurantList = DataFormatters.getDetailsFromRestaurants(restaurantRepository.findAll());
        return ResponseEntity.status(200).body(restaurantList);
    }

    public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant) throws ResourceAlreadyExistsException {
    	
    	Restaurant currentRestaurant = restaurantRepository.findByName(restaurant.getText());
    	
    	//check if restaurant being added already exists or not
    	if(currentRestaurant != null) {
    		throw new ResourceAlreadyExistsException("Restaurant with the name " + restaurant.getText() + " exists. Please Enter a different name");
    	}
    	
        return ResponseEntity.status(201).body(restaurantRepository.save(restaurant));
    }

    public ResponseEntity<Restaurant> updateRestaurant(int id, Restaurant updatedRestaurant) throws ResourceDoesNotExistException  {
        Restaurant currentRestaurant = restaurantRepository.findById(id).orElseThrow(
        		() -> new ResourceDoesNotExistException("Restaurant with id = " + id + " does not exist"));
        
        	currentRestaurant.setAddress(updatedRestaurant.getAddress());
        	currentRestaurant.setDescription(updatedRestaurant.getDescription());
        	currentRestaurant.setText(updatedRestaurant.getText());
        	currentRestaurant.setCuisine(updatedRestaurant.getCuisine());
        	
	
        return ResponseEntity.status(200).body(restaurantRepository.save(currentRestaurant));
    }
    
//    public ResponseEntity<Restaurant> getSingleRestaurantById(int id){
//    	 Restaurant singleRestaurant = restaurantRepository.findById(id).orElseThrow(
//         		() -> new IllegalStateException("Restaurant with id = " + id + " does not exist"));
//    	
//    	return ResponseEntity.status(200).body(singleRestaurant);
//    }
    
    public RestaurantCompleteInfo getSingleRestaurantById(int id) {
    	Restaurant singleRestaurant = restaurantRepository.findById(id).orElseThrow(
    			() -> new IllegalStateException("Restaurant with id = " + id + " does not exist"));
    	
    	return new RestaurantCompleteInfo(singleRestaurant);
    }

}
