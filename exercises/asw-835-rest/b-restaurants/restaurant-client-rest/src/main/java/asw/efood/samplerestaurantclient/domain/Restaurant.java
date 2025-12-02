package asw.efood.samplerestaurantclient.domain;

import lombok.*; 

// è la controparte lato client dell'entità Restaurant nel dominio del server
@Data @NoArgsConstructor @AllArgsConstructor
public class Restaurant {

	private Long id; 
	private String name; 
	private String location; 
	
}
