package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository <Restaurant, Integer> {
	
	
	@Query("select r from Restaurant r where r.text = ?1")
	 Restaurant findByName(String name);

}
