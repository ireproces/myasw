package asw.bettermusic.recensioniseguite.domain;

import lombok.*; 

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;

/* Un album. */  
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Album {

	@Id
	/* id dell'album */ 
	private Long id; 
	/* titolo dell'album */ 
	private String titolo; 
	/* artista dell'album */ 
	private String artista; 
	/* generi dell'album */
	@ElementCollection
	private Set<String> generi; 
	
}
