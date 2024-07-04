
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;


@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final Double peakHoursServingRadiusInKms = 3.0;
    private final Double normalHoursServingRadiusInKms = 5.0;

    @Autowired
    private RestaurantRepositoryService restaurantRepositoryService;

    @Override
    public GetRestaurantsResponse findAllRestaurantsCloseBy(
            GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        Double servingRadius;

        // Check if the current time falls within peak hours
        if (isPeakHour(currentTime)) {
            servingRadius = peakHoursServingRadiusInKms;
        } else {
            servingRadius = normalHoursServingRadiusInKms;
        }

        // Call the repository service with the determined serving radius
        List<Restaurant> restaurants = restaurantRepositoryService.findAllRestaurantsCloseBy(
                getRestaurantsRequest.getLatitude(),
                getRestaurantsRequest.getLongitude(),
                currentTime,
                servingRadius);

        // Return the response
        return new GetRestaurantsResponse(restaurants);
    }

    // Method to check if the given time is within peak hours
    private boolean isPeakHour(LocalTime currentTime) {
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
    
        // Check if the time falls within any of the peak hour ranges
        if ((currentTime.isAfter(LocalTime.parse("07:59:00.00")) && currentTime.isBefore(LocalTime.parse("10:01:00.00"))) || 
            (currentTime.isAfter(LocalTime.parse("12:59:00.00")) && currentTime.isBefore(LocalTime.parse("14:01:00.00"))) || 
            (currentTime.isAfter(LocalTime.parse("18:59:00.00")) && currentTime.isBefore(LocalTime.parse("21:01:00.00")))) {
            return true;
        }
        return false;
    }
    
}


  

  // @Override
  // public GetRestaurantsResponse findAllRestaurantsCloseBy(
  //     GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
  //   List<Restaurant> allRestaurants = fetchAllRestaurants();
  //   Local location = getRestaurantsRequest.getLocation();
  //   List<Restaurant> nearbyRestaurants = filterByDistance(allRestaurants, location);
  //   List<Restaurant> openRestaurants = filterByOperatingHours(nearbyRestaurants, currentTime);
    
  //   return new GetRestaurantsResponse(openRestaurants);
  // }

  // private List<Restaurant> fetchAllRestaurants() {
  //   // Implement logic to fetch all restaurants from the database or any other source
  //   return null; // Placeholder
  // }

  // private List<Restaurant> filterByDistance(List<Restaurant> restaurants, Location location) {
  //   // Implement distance filtering logic using Haversine formula
  //   // Example: Calculate distance between each restaurant and the provided location
  //   // Filter out restaurants that are beyond a certain distance threshold
  //   return restaurants;
  // }

  // private List<Restaurant> filterByOperatingHours(List<Restaurant> restaurants, LocalTime currentTime) {
  //   // Implement operating hours filtering logic
  //   // Example: Filter out restaurants that are not open at the current time
  //   return restaurants;
  // }






