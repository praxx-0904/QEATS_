package com.crio.qeats.repositories;

import com.crio.qeats.models.RestaurantEntity;
import java.util.List; // Import List class from java.util package

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {
    List<RestaurantEntity> findAllByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLon, double maxLon);
}



