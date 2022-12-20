package or.lv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import or.lv.domain.Valuta;
import or.lv.repository.ValutaRepository;
import or.lv.service.RequestDeniedException;
import or.lv.service.ValutaService;

@Service
public class ValutaServiceImpl implements ValutaService {
	
	@Autowired
	private ValutaRepository valutaRepository;

	@Override
	public Optional<Valuta> findById(String id) {
		return valutaRepository.findById(id);
	}

	@Override
	public Valuta createCurrency(Valuta currency) {
		Assert.notNull(currency, "Currency object must be given!");
		
		String isoOznaka = currency.getIsoOzn();
		Assert.hasText(isoOznaka, "ISO mark must be given!");
		if (!valutaRepository.findById(isoOznaka).isEmpty()) {
			throw new RequestDeniedException("Currency with ISO mark " + isoOznaka + " already exists.");
		}
		
		String naziv = currency.getNaziv();
		Assert.hasText(naziv, "Currency name must be given!");
		
		return valutaRepository.save(currency);
	}

	@Override
	public Optional<Valuta> getById(String isoOzn) {
		return valutaRepository.findById(isoOzn);
	}

	@Override
	public Valuta updateCurrency(Valuta valuta) {
		Assert.notNull(valuta, "Currency must be given!");
		Valuta pohranjena = valutaRepository.findById(valuta.getIsoOzn()).get();
		
		String isoOzn = valuta.getIsoOzn();
		if (isoOzn != null && !isoOzn.isEmpty()) pohranjena.setIsoOzn(isoOzn);
		String naziv = valuta.getNaziv();
		if (naziv != null && !naziv.isEmpty()) pohranjena.setNaziv(naziv);
		
		return valutaRepository.save(pohranjena);
	}
}
