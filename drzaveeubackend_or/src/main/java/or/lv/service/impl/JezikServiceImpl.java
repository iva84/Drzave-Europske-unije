package or.lv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import or.lv.domain.Jezik;
import or.lv.repository.JezikRepository;
import or.lv.service.JezikService;
import or.lv.service.RequestDeniedException;

@Service
public class JezikServiceImpl implements JezikService {
	
	@Autowired
	private JezikRepository jezikRepository;

	@Override
	public Optional<Jezik> findById(String id) {
		return jezikRepository.findById(id);
	}

	
	@Override
	public Jezik createLanguage(Jezik language) {
		Assert.notNull(language, "Language object must be given!");
		
		String isoOznaka = language.getIsoOzn();
		Assert.hasText(isoOznaka, "ISO mark must be given!");
		if (!jezikRepository.findById(isoOznaka).isEmpty()) {
			throw new RequestDeniedException("Language with ISO mark " + isoOznaka + " already exists.");
		}
		
		String naziv = language.getNaziv();
		Assert.hasText(naziv, "Language name must be given!");
		
		return jezikRepository.save(language);
	}
	
	@Override
	public Optional<Jezik> getById(String isoOzn) {
		return jezikRepository.findById(isoOzn);
	}


	@Override
	public Jezik updateLanguage(Jezik jezik) {
		Assert.notNull(jezik, "Languge must be given!");
		Jezik pohranjeni = jezikRepository.findById(jezik.getIsoOzn()).get();
		
		String naziv = jezik.getNaziv();
		if (naziv != null && !naziv.isEmpty()) pohranjeni.setNaziv(naziv);
		
		return jezikRepository.save(pohranjeni);
	}


}
