package or.lv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import or.lv.domain.Osoba;
import or.lv.repository.OsobaRepository;
import or.lv.service.OsobaService;
import or.lv.service.RequestDeniedException;

@Service
public class OsobaServiceImpl implements OsobaService {
	
	@Autowired
	private OsobaRepository osobaRepository;

	@Override
	public Osoba createPerson(Osoba person) {
		Assert.notNull(person, "Person object must be given!");
		
		Assert.isNull(person.getOsobaId(), "Person ID must be null!");
		Assert.notNull(person.getIme(), "Person firstname must be given!");
		Assert.notNull(person.getPrezime(), "Person lastname must be given!");
		Assert.notNull(person.getUloga(), "Person role must be given!");
		
		return osobaRepository.save(person);
	}
	
	@Override
	public Optional<Osoba> getById(Integer osobaId) {
		return osobaRepository.findById(osobaId);
	}

	@Override
	public Osoba updatePerson(Osoba osoba) {
		Assert.notNull(osoba, "Person object must be given!");
		Osoba pohranjena = osobaRepository.findById(osoba.getOsobaId()).get();
		
		String ime = osoba.getIme();
		if (ime != null && !ime.isEmpty()) pohranjena.setIme(ime);
		String prezime = osoba.getPrezime();
		if (prezime != null && !prezime.isEmpty()) pohranjena.setPrezime(prezime);
		String uloga = osoba.getUloga();
		if (uloga != null && !uloga.isEmpty()) pohranjena.setUloga(uloga);
		
		return osobaRepository.save(pohranjena);
	}
	
}
