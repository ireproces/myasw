package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

// interfaccia richiesta per accedere al servizio
// è la controparte lato client di RestaurantService nel dominio del server
public interface RestaurantClientPort {

	Long createRestaurant(String name, String location) throws RestaurantServiceException;
	Restaurant getRestaurant(Long restaurantId) throws RestaurantServiceException;
	Restaurant getRestaurantByNameAndLocation(String name, String location) throws RestaurantServiceException;
	List<Restaurant> getAllRestaurants();
	List<Restaurant> getRestaurantsByLocation(String location);

}
