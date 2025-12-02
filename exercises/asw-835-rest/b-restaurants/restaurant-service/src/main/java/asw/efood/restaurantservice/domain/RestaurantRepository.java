package asw.efood.restaurantservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

// repository JPA per l'accesso ai ristoranti
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

 	Optional<Restaurant> findByNameAndLocation(String name, String location); 

	Collection<Restaurant> findAll();
	
	Collection<Restaurant> findAllByLocation(String location);

}

