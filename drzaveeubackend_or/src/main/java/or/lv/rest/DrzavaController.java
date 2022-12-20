package or.lv.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import or.lv.domain.Drzava;
import or.lv.service.DrzavaService;

@RestController
@RequestMapping("/drzave")
@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
public class DrzavaController {
	
	@Autowired
	private DrzavaService drzavaService;
	
	// cijela kolekcija
	@GetMapping("")
	public ResponseEntity<?> listAllCountries() {
		List<Drzava> respBody = drzavaService.listAll();
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Dohvacena lista svih drzava.");
		props.put("status", "OK");
		props.put("response", respBody);
		return new ResponseEntity<>(props, HttpStatus.OK);
	}
	
	// pojedinacni resurs -> moze ne postojati -> 404
	@GetMapping("/{isoOznaka}")
	public ResponseEntity<?> getCountryById(@PathVariable String isoOznaka) {
		Drzava respBody = drzavaService.findById(isoOznaka);
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Dohvacena drzava sa ISO oznakom " + isoOznaka);
		props.put("status", "OK");
		props.put("response", respBody);
		return new ResponseEntity<>(props, HttpStatus.OK);
	}
	
	
	// ubacivanje pojedinog resursa
	@PostMapping("")
	public ResponseEntity<?> createCountry(@RequestBody Drzava drzava) {
		Drzava respBody = drzavaService.createCountry(drzava);
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Kreirana drzava sa ISO oznakom " + respBody.getIsoOznaka());
		props.put("status", "Created");
		props.put("response", respBody);
		return new ResponseEntity<>(props, HttpStatus.CREATED);
	}
	
	// azuriranje elemenata resursa
	@PutMapping("/{isoOznaka}")
	public ResponseEntity<?> updateCountry(@RequestBody Drzava drzava) {
		Drzava respBody = drzavaService.updateCoutry(drzava);
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Azurirana drzava sa ISO oznakom " + respBody.getIsoOznaka());
		props.put("status", "OK");
		props.put("response", respBody);
		return new ResponseEntity<>(props, HttpStatus.OK);
	}
	
	// brisanje resursa
	@DeleteMapping("/{isoOznaka}")
	public ResponseEntity<?> deleteCountry(@PathVariable String isoOznaka) {
		drzavaService.deleteById(isoOznaka);
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Izbrisana drzava sa ISO oznakom " + isoOznaka);
		props.put("status", "OK");
		props.put("response", null);
		return new ResponseEntity<>(props, HttpStatus.OK);
	}
	
	// openAPI
	@GetMapping("openAPI")
	public ResponseEntity<?> getOpenAPI() {
		String respBody = drzavaService.getOpenAPI();
		
		Map<String, Object> props = new HashMap<>();
		props.put("message", "Dohvacena OpenAPI specifikacija!");
		props.put("status", "OK");
		props.put("response", respBody);
		return new ResponseEntity<>(props, HttpStatus.OK);
	}
	
	// FILTER
	
	@GetMapping(path = "/filter/json")
	public Set<Drzava> listFilterJSON(@RequestBody DrzavaFilterDTO drzavaFilterDTO) {
		return drzavaService.listByFilter(drzavaFilterDTO);
	}
	
	@GetMapping(path = "/filter/csv", produces = "text/csv")
	public ResponseEntity<InputStreamResource> listFilterCSV(@RequestBody DrzavaFilterDTO drzavaFilterDTO) {
		Set<Drzava> filtrirano = drzavaService.listByFilter(drzavaFilterDTO);
		return drzavaService.convertContrySetToCSV(filtrirano);
	}
	
	@GetMapping(path = "/isoOznaka")
	public List<Drzava> listByISOContaining(@RequestParam String isoOznaka) {;
		return drzavaService.findByISOContaining(isoOznaka);
	}
	
	@GetMapping(path = "/naziv")
	public List<Drzava> listByNameContaining(@RequestParam String naziv) {;
		return drzavaService.findByNameContaining(naziv);
	}
	
	@GetMapping(path = "/puniNaziv")
	public List<Drzava> listByFullNameContaining(@RequestParam String puniNaziv) {;
		return drzavaService.listByFullNameContaining(puniNaziv);
	}
	
	// TODO dodatno poboljsat --> LocalDate
	@GetMapping(path = "/datumUlaskaUEU")
	public List<Drzava> listByEUDateContaining(@RequestParam String datumUlaskaUEU) {;
		return drzavaService.listByEUDateContaining(datumUlaskaUEU);
	}
	
	// TODO dodatno poboljsat --> Double
	@GetMapping(path = "/povrsina")
	public List<Drzava> listBySurfaceContaining(@RequestParam String povrsina) {;
		return drzavaService.listBySurfaceContaining(povrsina);
	}
	
	// TODO poboljsat --> Integer
	@GetMapping(path = "/brojStanovnika")
	public List<Drzava> listByPopulationContaining(@RequestParam String brojStanovnika) {;
		return drzavaService.listByPopulationContaining(brojStanovnika);
	}
	
	@GetMapping(path = "/nazivHimne")
	public List<Drzava> listByAnthemContaining(@RequestParam String nazivHimne) {;
		return drzavaService.listByAnthemContaining(nazivHimne);
	}
	
	@GetMapping(path = "/glavniGrad")
	public List<Drzava> listByCapitalCityContaining(@RequestParam String glavniGrad) {;
		return drzavaService.listByCapitalCityContaining(glavniGrad);
	}
	
	// jezik
	// valuta
	// drzavni vrh
}
