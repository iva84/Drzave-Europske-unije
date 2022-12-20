package or.lv.service;

import java.util.List;
import java.util.Set;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import or.lv.domain.Drzava;
import or.lv.rest.DrzavaFilterDTO;
import or.lv.rest.DrzavaUpdateDTO;

public interface DrzavaService {

	List<Drzava> listAll();

	Drzava createCountry(Drzava drzava);

	List<Drzava> findByNameContaining(String naziv);

	List<Drzava> listByFullNameContaining(String puniNaziv);

	List<Drzava> findByISOContaining(String isoOznaka);

	List<Drzava> listByEUDateContaining(String datumUlaskaUEU);

	List<Drzava> listBySurfaceContaining(String povrsina);

	List<Drzava> listByPopulationContaining(String brojStanovnika);

	List<Drzava> listByAnthemContaining(String nazivHimne);

	List<Drzava> listByCapitalCityContaining(String glavniGrad);
	
	Set<Drzava> listByFilter(DrzavaFilterDTO drzavaFilterDTO);

	ResponseEntity<InputStreamResource> convertContrySetToCSV(Set<Drzava> filtrirano);

	Drzava findById(String isoOznaka);

	void deleteById(String isoOznaka);

	Drzava updateCoutry(Drzava drzava);

	String getOpenAPI();

}
