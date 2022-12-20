package or.lv.service;

import java.util.Optional;

import or.lv.domain.Valuta;

public interface ValutaService {

	Optional<Valuta> findById(String id);

	Valuta createCurrency(Valuta valuta);

	Optional<Valuta> getById(String isoOzn);

	Valuta updateCurrency(Valuta valuta);

}
