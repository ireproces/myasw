package asw.efood.restaurantservice.domain;

import java.util.*; 

// interfaccia per la gestione dei ristoranti
public interface RestaurantService {

	// operazioni del servizio, esposte con REST

	Restaurant createRestaurant(String name, String location) throws RestaurantServiceException; 
	Restaurant getRestaurant(Long id) throws RestaurantServiceException; 
	Restaurant getRestaurant(String name, String location) throws RestaurantServiceException; 
	Collection<Restaurant> getAllRestaurants(); 
	Collection<Restaurant> getRestaurantsByLocation(String location); 

}

