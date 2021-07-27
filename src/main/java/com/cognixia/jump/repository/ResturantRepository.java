package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Restaurant;

@Repository
public interface ResturantRepository extends JpaRepository <Restaurant, Integer> {

}
