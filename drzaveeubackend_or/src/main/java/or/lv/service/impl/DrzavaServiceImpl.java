package or.lv.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import or.lv.domain.Drzava;
import or.lv.domain.Jezik;
import or.lv.domain.Osoba;
import or.lv.domain.Valuta;
import or.lv.repository.DrzavaRepository;
import or.lv.repository.JezikRepository;
import or.lv.rest.DrzavaFilterDTO;
import or.lv.rest.DrzavaUpdateDTO;
import or.lv.service.DrzavaService;
import or.lv.service.JezikService;
import or.lv.service.OsobaService;
import or.lv.service.RequestDeniedException;
import or.lv.service.ResourceNotFoundException;
import or.lv.service.ValutaService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class DrzavaServiceImpl implements DrzavaService {

	@Autowired
	private DrzavaRepository drzavaRepository;
	
	@Autowired
	private JezikService jezikService;
	
	@Autowired
	private ValutaService valutaService;
	
	@Autowired
	private OsobaService osobaService;
	
	
	@Override
	public List<Drzava> listAll() {
		return drzavaRepository.findAll();
	}
	
	@Override
	public Drzava findById(String isoOznaka) {
		Optional<Drzava> drzava = drzavaRepository.findById(isoOznaka);
		if (!drzava.isPresent()) {
			throw new ResourceNotFoundException("Contry with ISO mark " + isoOznaka + " does not exists!");
		}
		return drzava.get();
	}

	
	@Override
	public void deleteById(String isoOznaka) {
		drzavaRepository.deleteById(isoOznaka);
	}
	
	@Override
	public String getOpenAPI() {
		// procitaj iz datoteke i vrati string
		return null;
	}
	
	// ako se preda prazno, ne radi se update
	// ako se preda neka vrijednost, cijela stara se brise i postavlja se predana
	// TODO mozda da predamo skroz novu nije ni to lose, to je jos lakse za impl
	@Override
	public Drzava updateCoutry(Drzava drzavaUpdate) {
		Assert.notNull(drzavaUpdate, "Contry object must be given!");
		
		Optional<Drzava> drzavaOptional = drzavaRepository.findById(drzavaUpdate.getIsoOznaka());
		if (!drzavaOptional.isPresent()) {
			throw new ResourceNotFoundException("Country with ISO mark " + drzavaUpdate.getIsoOznaka() + " does not extsts! "
					+ "You can't change ISO matk!");
		}
		Drzava pohranjena = drzavaOptional.get();
		
		if (!drzavaUpdate.getNaziv().isEmpty()) pohranjena.setNaziv(drzavaUpdate.getNaziv());
		if (!drzavaUpdate.getPuniNaziv().isEmpty()) pohranjena.setPuniNaziv(drzavaUpdate.getPuniNaziv());
		if (drzavaUpdate.getDatumUlaskaUEU() != null) pohranjena.setDatumUlaskaUEU(drzavaUpdate.getDatumUlaskaUEU());
		if (drzavaUpdate.getPovrsina() != null) pohranjena.setPovrsina(drzavaUpdate.getPovrsina());
		if (drzavaUpdate.getBrojStanovnika() != null) pohranjena.setBrojStanovnika(drzavaUpdate.getBrojStanovnika());
		if (!drzavaUpdate.getNazivHimne().isEmpty()) pohranjena.setNazivHimne(drzavaUpdate.getNazivHimne());
		if (!drzavaUpdate.getGlavniGrad().isEmpty()) pohranjena.setGlavniGrad(drzavaUpdate.getGlavniGrad());
		if (drzavaUpdate.getSluzbeniJezik() != null && drzavaUpdate.getSluzbeniJezik().size() > 0) {
			Set<Jezik> noviSluzbeniJezik = new HashSet<>();
			for (Jezik noviJezik : drzavaUpdate.getSluzbeniJezik()) {
				Optional<Jezik> jezikOpt = jezikService.getById(noviJezik.getIsoOzn());
				if (!jezikOpt.isPresent()) {
					Jezik j = jezikService.createLanguage(noviJezik);
					noviSluzbeniJezik.add(j);
				} else {
					Jezik j = jezikService.updateLanguage(noviJezik);
					noviSluzbeniJezik.add(j);
				}
			}
			pohranjena.setSluzbeniJezik(noviSluzbeniJezik);
		}
		if (drzavaUpdate.getSluzbenaValuta() != null && drzavaUpdate.getSluzbenaValuta().size() > 0) {
			Set<Valuta> novaSluzbenaValuta = new HashSet<>();
			for (Valuta novaValuta : drzavaUpdate.getSluzbenaValuta()) {
				Optional<Valuta> valutaOpt = valutaService.getById(novaValuta.getIsoOzn());
				if (!valutaOpt.isPresent()) {
					Valuta v = valutaService.createCurrency(novaValuta);
					novaSluzbenaValuta.add(v);
				} else {
					Valuta v = valutaService.updateCurrency(novaValuta);
					novaSluzbenaValuta.add(v);
				}
			}
			pohranjena.setSluzbenaValuta(novaSluzbenaValuta);
		}
		if (drzavaUpdate.getDrzavniVrh() != null && drzavaUpdate.getDrzavniVrh().size() > 0) {
			Set<Osoba> noviDrzavniVrh = new HashSet<>();
			for (Osoba novaOsoba : drzavaUpdate.getDrzavniVrh()) {
				// nova osoba nema id, ako već postoji ima
				Optional<Osoba> osobaOpt = osobaService.getById(novaOsoba.getOsobaId());
				if (!osobaOpt.isPresent()) {
					Osoba o = osobaService.createPerson(novaOsoba);
					noviDrzavniVrh.add(o);
				} else {
					Osoba o = osobaService.updatePerson(novaOsoba);
					noviDrzavniVrh.add(o);
				}
			}
			pohranjena.setDrzavniVrh(noviDrzavniVrh);
		}
		return drzavaRepository.save(pohranjena);
	}
	
	
	@Override
	public Drzava createCountry(Drzava drzava) {
		Assert.notNull(drzava, "Coutry object must be given!");
		
		String isoOznaka = drzava.getIsoOznaka();
		Assert.hasText(isoOznaka, "Coutry ISO mark must be given!");
		Optional<Drzava> drzavaOptional = drzavaRepository.findById(isoOznaka);
		if (drzavaOptional.isPresent()) {
			throw new RequestDeniedException("Country with ISO mark " + isoOznaka + " already exists!");
		}
		
		Assert.hasText(drzava.getNaziv(), "Country name must be given!");
		Assert.hasText(drzava.getPuniNaziv(), "Country full name must be given!");
		Assert.hasText(drzava.getNazivHimne(), "County national anthem must be given!");
		Assert.hasText(drzava.getGlavniGrad(), "Country capital city must be given!");
		Assert.hasText(drzava.getBrojStanovnika().toString(), "Country population must be given!");
		Assert.hasText(drzava.getDatumUlaskaUEU().toString(), "Date of entry of the country into the European Union must be given!");
		Assert.hasText(drzava.getPovrsina().toString(), "COuntry surface must be given!");
		
		// prvo treba dohvatit sve jezike
		// ovo bi trebalo radit u petlji za sve navedene jezike
		// izvuci jezik
		// dohvati jezike
		Set<Jezik> jezici = drzava.getSluzbeniJezik();
		/*
		jezici.stream().forEach(jezik -> {
			jezikService.createLanguage(Optional.of(jezik));
		});
		*/
		// ovdje provjeravamo da li već postoji
		// jer moramo ga ubaciti ako ne postoji
		// a ako postoji kod se normalno nastavlja izvrsavati 
		// a metoda createLanguage inace ako ne postoji ne vraca 200
		jezici.stream().forEach(jezik -> {
			Optional<Jezik> jezikOptional = jezikService.findById(jezik.getIsoOzn());
			if (!jezikOptional.isPresent()) {
				jezikService.createLanguage(jezik);
			}
		});

		// valute
		Set<Valuta> valute = drzava.getSluzbenaValuta();
		/*
		valute.stream().forEach(valuta -> {
			valutaService.createCurrency(Optional.of(valuta));
		});
		*/
		valute.stream().forEach(valuta -> {
			Optional<Valuta> valutaOptional = valutaService.findById(valuta.getIsoOzn());
			if (!valutaOptional.isPresent()) {
				valutaService.createCurrency(valuta);
			}
		});
		
		// drzavni vrh svakako ne treba postojati
		// ako i postoji neki sa istim imenom i prezimenom i ulogom id je razlicit
		Set<Osoba> drzavniVrh = drzava.getDrzavniVrh();
		drzavniVrh.stream().forEach(osoba -> {
			osobaService.createPerson(osoba);
		});
		
		return drzavaRepository.save(drzava);
	}

	// FILTER
	
	@Override
	public List<Drzava> findByNameContaining(String naziv) {
		return drzavaRepository.findByNameContaining(naziv.toLowerCase());
	}

	@Override
	public List<Drzava> listByFullNameContaining(String puniNaziv) {
		return drzavaRepository.findByFullNameContaining(puniNaziv.toLowerCase());
	}

	@Override
	public List<Drzava> findByISOContaining(String isoOznaka) {
		return drzavaRepository.findByISOContaining(isoOznaka.toLowerCase());
	}

	@Override
	public List<Drzava> listByEUDateContaining(String datumUlaskaUEU) {
		return drzavaRepository.findByEUDateContaining(datumUlaskaUEU.toLowerCase());
	}

	@Override
	public List<Drzava> listBySurfaceContaining(String povrsina) {
		return drzavaRepository.findBySurfaceContaining(povrsina.toLowerCase());
	}
	
	@Override
	public List<Drzava> listByPopulationContaining(String brojStanovnika) {
		return drzavaRepository.findByPopulationContaining(brojStanovnika.toLowerCase());
	}
	
	@Override
	public List<Drzava> listByAnthemContaining(String nazivHimne) {
		return drzavaRepository.findByAnthemContaining(nazivHimne.toLowerCase());
	}
	
	@Override
	public List<Drzava> listByCapitalCityContaining(String glavniGrad) {
		return drzavaRepository.findByCapitalCityContaining(glavniGrad.toLowerCase());
	}
	
	// opceniti filter po pojedinim stupcima (moze biti ukljucen jedan stupac a moze biti i vise)
	// ako iz ovog zelimo dobiti pretrazivanje po svim stupcima
	// samo moramo poslati vrijednost koju je korisnik unio na sve atribute
	// znaci svi atributi trebaju imati istu vrijednost
	// ali u tom slucaju stalno radimo addAll jer dodajemo sve koji zadovoljavaju, ne iskljucujemo
	// moramo naznaciti da se radi o najopcenitijem filteru
	// ta naznaka je varijabla u klasi DTO
	// ako je ona true radi se najopcenitija
	// ako je false radi se po stupcima (svaki stupac ima svoju vrijednost)
	@Override
	public Set<Drzava> listByFilter(DrzavaFilterDTO drzavaFilterDTO) {
		
		if (drzavaFilterDTO.isNajopcenitijiFilter() == null) {
			throw new RequestDeniedException("Filtering mode must be given!");
		}
		boolean najopcenitijiFilter = drzavaFilterDTO.isNajopcenitijiFilter();
		
		Set<Drzava> filtered = new HashSet<>();
		
		if (!drzavaFilterDTO.getIsoOznaka().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByISOContaining(drzavaFilterDTO.getIsoOznaka().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getNaziv().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByNameContaining(drzavaFilterDTO.getNaziv().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getPuniNaziv().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByFullNameContaining(drzavaFilterDTO.getPuniNaziv().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		// TODO ako ne zadovoljava format preskoci
		if (drzavaFilterDTO.getDatumUlaskaUEU() != null && !drzavaFilterDTO.getDatumUlaskaUEU().toString().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByEUDateContaining(drzavaFilterDTO.getDatumUlaskaUEU().toString().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		// TODO ako nije double preskoci
		if (drzavaFilterDTO.getPovrsina() != null && !drzavaFilterDTO.getPovrsina().toString().isEmpty()) {
			List<Drzava> res = drzavaRepository.findBySurfaceContaining(drzavaFilterDTO.getPovrsina().toString().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		// TODO ako nije Integer preskoci
		if (drzavaFilterDTO.getBrojStanovnika() != null && !drzavaFilterDTO.getBrojStanovnika().toString().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByPopulationContaining(drzavaFilterDTO.getBrojStanovnika().toString().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getNazivHimne().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByAnthemContaining(drzavaFilterDTO.getNazivHimne().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getGlavniGrad().isEmpty()) {
			List<Drzava> res = drzavaRepository.findByCapitalCityContaining(drzavaFilterDTO.getGlavniGrad().toLowerCase());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		// trebamo pronaci drzavu koja sadrzi taj jezik sa tom iso oznakom
		if (!drzavaFilterDTO.getSluzbenaValutaISOozn().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getSluzbenaValuta()
											.stream()
											.anyMatch(valuta -> valuta.getIsoOzn().toLowerCase()
																.contains(drzavaFilterDTO.getSluzbenaValutaISOozn().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getSluzbenaValutaNaziv().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getSluzbenaValuta()
											.stream()
											.anyMatch(valuta -> valuta.getNaziv().toLowerCase()
																.contains(drzavaFilterDTO.getSluzbenaValutaNaziv().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getSluzbeniJezikISOozn().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getSluzbeniJezik()
											.stream()
											.anyMatch(jezik -> jezik.getIsoOzn().toLowerCase()
																.contains(drzavaFilterDTO.getSluzbeniJezikISOozn().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getSluzbeniJezikNaziv().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getSluzbeniJezik()
											.stream()
											.anyMatch(jezik -> jezik.getNaziv().toLowerCase()
																.contains(drzavaFilterDTO.getSluzbeniJezikNaziv().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getDrzavniVrhIme().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getDrzavniVrh()
											.stream()
											.anyMatch(osoba -> osoba.getIme().toLowerCase()
																.contains(drzavaFilterDTO.getDrzavniVrhIme().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getDrzavniVrhPrezime().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getDrzavniVrh()
											.stream()
											.anyMatch(osoba -> osoba.getPrezime().toLowerCase()
																.contains(drzavaFilterDTO.getDrzavniVrhPrezime().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		if (!drzavaFilterDTO.getDrzavniVrhUloga().isEmpty()) {
			Set<Drzava> res = drzavaRepository.findAll()
					.stream()
					.filter(drzava -> drzava.getDrzavniVrh()
											.stream()
											.anyMatch(osoba -> osoba.getUloga().toLowerCase()
																.contains(drzavaFilterDTO.getDrzavniVrhUloga().toLowerCase())))
					.collect(Collectors.toSet());
			if (filtered.isEmpty() || najopcenitijiFilter) filtered.addAll(res);
			else filtered.retainAll(res);
		}
		
		// JSON
		return filtered;
	}

	@Override
	public ResponseEntity<InputStreamResource> convertContrySetToCSV(Set<Drzava> filtered) {
		// CSV
		// https://codeburst.io/returning-csv-content-from-an-api-in-spring-boot-63ea82bbcf0f
		String[] csvHeader = {
				"isoOznaka", "naziv", "puniNaziv", "datumUlaskaUEU", "povrsina",
				"brojStanovnika", "nazivHimne", "glavniGrad", "sluzbeniJezikISOozn", 
				"sluzbeniJezikNaziv", "sluzbenaValutaISOozn", "sluzbenaValutaNaziv", 
				"drzavniVrhIme", "drzavniVrhPrezime", "drzavniVrhUloga"
		};
		List<List<String>> csvBody = new ArrayList<>();
		filtered.stream()
			.forEach(drzava -> {
				List<String> row = new ArrayList<>();
				row.add(drzava.getIsoOznaka());
				row.add(drzava.getNaziv());
				row.add(drzava.getPuniNaziv());
				row.add(drzava.getDatumUlaskaUEU().toString());
				row.add(drzava.getPovrsina().toString());
				row.add(drzava.getBrojStanovnika().toString());
				row.add(drzava.getNazivHimne());
				row.add(drzava.getGlavniGrad());
				String sluzbeniJezikISO = "";
				String sluzbeniJezikNaziv = "";
				List<Jezik> jezici = new ArrayList<>(drzava.getSluzbeniJezik());
				for (int i = 0; i < jezici.size(); i++) {
					sluzbeniJezikISO += jezici.get(i).getIsoOzn();
					sluzbeniJezikNaziv += jezici.get(i).getNaziv();
					if (i < jezici.size() - 1) {
						sluzbeniJezikISO += ", ";
						sluzbeniJezikNaziv += ", ";
					}
				}
				row.add(sluzbeniJezikISO);
				row.add(sluzbeniJezikNaziv);
				String sluzbenaValutaISO = "";
				String sluzbenaValutaNaziv = "";
				List<Valuta> valute = new ArrayList<>(drzava.getSluzbenaValuta());
				for (int i = 0; i < valute.size(); i++) {
					sluzbenaValutaISO += valute.get(i).getIsoOzn();
					sluzbenaValutaNaziv += valute.get(i).getNaziv();
					if (i < valute.size() - 1) {
						sluzbenaValutaISO += ", ";
						sluzbenaValutaNaziv += ", ";
					}
				}
				row.add(sluzbenaValutaISO);
				row.add(sluzbenaValutaNaziv);
				String drzavniVrhOsoba = "";
				List<Osoba> osobe = new ArrayList<>(drzava.getDrzavniVrh());
				for (int i = 0; i < osobe.size(); i++) {
					drzavniVrhOsoba += osobe.get(i).getIme();
					drzavniVrhOsoba += " ";
					drzavniVrhOsoba += osobe.get(i).getPrezime();
					drzavniVrhOsoba += " ";
					drzavniVrhOsoba += osobe.get(i).getUloga();
					if (i < osobe.size() - 1) {
						drzavniVrhOsoba += "\n";
					}
				}
				row.add(drzavniVrhOsoba);
			});
		ByteArrayInputStream byteArrInputStream = null;
		try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			 CSVPrinter csvPrinter = new CSVPrinter(
				new PrintWriter(outStream),
				CSVFormat.DEFAULT.withHeader(csvHeader)
			 );
		){
			
			for (List<String> record : csvBody) {
				csvPrinter.printRecord(record);
			}
			csvPrinter.flush();
			byteArrInputStream = new ByteArrayInputStream(outStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		InputStreamResource fileInputStream = new InputStreamResource(byteArrInputStream);
		String csvFileName = "drzaveCSV.csv";
		
		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
		header.set(HttpHeaders.CONTENT_TYPE, "text/csv");
		
		return new ResponseEntity<>(fileInputStream, header, HttpStatus.OK);
	}


}
