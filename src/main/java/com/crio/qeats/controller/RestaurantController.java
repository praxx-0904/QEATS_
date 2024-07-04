package com.crio.qeats.controller;

import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.services.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalTime;

@RestController
@RequestMapping(RestaurantController.RESTAURANT_API_ENDPOINT)
@Log4j2
public class RestaurantController {

    public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
    public static final String RESTAURANTS_API = "/restaurants";
    
    // Define additional constants
    public static final String MENU_API = "/menu";
    public static final String CART_API = "/cart";
    public static final String CART_ITEM_API = "/cart-item";
    public static final String CART_CLEAR_API = "/cart-clear";
    public static final String POST_ORDER_API = "/post-order";
    public static final String GET_ORDERS_API = "/get-orders";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(RESTAURANTS_API)
    public ResponseEntity<GetRestaurantsResponse> getRestaurants(
            @Valid GetRestaurantsRequest getRestaurantsRequest) {

        log.info("getRestaurants called with {}", getRestaurantsRequest);

        // Validate latitude and longitude
        if (getRestaurantsRequest.getLatitude() != null && getRestaurantsRequest.getLongitude() != null
                && isValidLatitude(getRestaurantsRequest.getLatitude())
                && isValidLongitude(getRestaurantsRequest.getLongitude())) {

            // Get restaurants if latitude and longitude are valid
            GetRestaurantsResponse getRestaurantsResponse = restaurantService
                    .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());

            log.info("getRestaurants returned {}", getRestaurantsResponse);

            return ResponseEntity.ok().body(getRestaurantsResponse);
        } else {
            // Return bad request response if latitude or longitude are invalid
            return ResponseEntity.badRequest().body(null);
        }
    }

    private boolean isValidLatitude(Double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private boolean isValidLongitude(Double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

}
