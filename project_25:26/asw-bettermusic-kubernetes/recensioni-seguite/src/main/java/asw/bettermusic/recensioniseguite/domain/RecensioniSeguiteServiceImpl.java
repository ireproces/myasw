package asw.bettermusic.recensioniseguite.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;

import java.util.*; 
import java.util.stream.*; 

@Service("RecensioniSeguiteServiceRestBasedImpl")
public class RecensioniSeguiteServiceImpl implements RecensioniSeguiteService {

    @Autowired private ConnessioniRepository connessioniRepository;
    @Autowired private RecensioniRepository recensioniRepository;
    @Autowired private AlbumRepository albumRepository;

    /* Trova le recensioni seguite da un utente, 
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */  
    public Collection<Recensione> getRecensioniSeguite(String utente) {
        
        Collection<Recensione> recensioniSeguite = new TreeSet<>(); 
        
        Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente); // QUERY 1

        // --- A. Recensioni dei Recensori Seguiti (1 QUERY) ---
        Collection<String> recensoriSeguiti = 
            connessioni.stream()
                .filter(c -> c.getRuolo().equals("RECENSORE"))
                .map(c -> c.getSeguito())
                .collect(Collectors.toSet()); 
        
        if (!recensoriSeguiti.isEmpty()) {
            // Usa il nuovo metodo findByRecensoreIn per fare una singola query
            Collection<Recensione> recensioniDiRecensoreSeguito = recensioniRepository.findByRecensoreIn(recensoriSeguiti); // QUERY 2
            recensioniSeguite.addAll(recensioniDiRecensoreSeguito); 
        }

        // --- B. Recensioni degli Artisti Seguiti (2 QUERY) ---
        Collection<String> artistiSeguiti = 
            connessioni.stream()
                .filter(c -> c.getRuolo().equals("ARTISTA"))
                .map(c -> c.getSeguito())
                .collect(Collectors.toSet()); 

        if (!artistiSeguiti.isEmpty()) {
            // 1. Trova TUTTI gli Album per TUTTI gli artisti seguiti (1 QUERY)
            Collection<Album> albumDiArtistiSeguiti = albumRepository.findByArtistaIn(artistiSeguiti); // QUERY 3
            
            // Raccogli solo gli ID degli album
            Collection<Long> idAlbumDiArtistiSeguiti = 
                albumDiArtistiSeguiti.stream()
                    .map(Album::getId)
                    .collect(Collectors.toSet());

            if (!idAlbumDiArtistiSeguiti.isEmpty()) {
                // 2. Trova TUTTE le Recensioni per TUTTI gli ID di album raccolti (1 QUERY)
                Collection<Recensione> recensioniDiArtistiSeguiti = recensioniRepository.findByIdAlbumIn(idAlbumDiArtistiSeguiti); // QUERY 4
                recensioniSeguite.addAll(recensioniDiArtistiSeguiti); 
            }
        }
        
        // --- C. Recensioni dei Generi Seguiti (2 QUERY) ---
        Collection<String> generiSeguiti = 
            connessioni.stream()
                .filter(c -> c.getRuolo().equals("GENERE"))
                .map(c -> c.getSeguito())
                .collect(Collectors.toSet()); 

        if (!generiSeguiti.isEmpty()) {
            // 1. Trova TUTTI gli Album per TUTTI i generi seguiti (1 QUERY)
            // Usa il nuovo metodo @Query(value = "... WHERE g IN :generi")
            Collection<Album> albumDiGeneriSeguiti = albumRepository.findByGenereIn(generiSeguiti); // QUERY 5

            // Raccogli solo gli ID degli album
            Collection<Long> idAlbumDiGeneriSeguiti = 
                albumDiGeneriSeguiti.stream()
                    .map(Album::getId)
                    .collect(Collectors.toSet());
            
            if (!idAlbumDiGeneriSeguiti.isEmpty()) {
                // 2. Trova TUTTE le Recensioni per TUTTI gli ID di album raccolti (1 QUERY)
                Collection<Recensione> recensioniDiGeneriSeguiti = recensioniRepository.findByIdAlbumIn(idAlbumDiGeneriSeguiti); // QUERY 6
                recensioniSeguite.addAll(recensioniDiGeneriSeguiti); 
            }
        }

        return recensioniSeguite; 
    }
}