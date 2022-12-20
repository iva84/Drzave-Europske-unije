package or.lv.service;

import java.util.Optional;

import or.lv.domain.Osoba;

public interface OsobaService {

	Osoba createPerson(Osoba of);

	Optional<Osoba> getById(Integer osobaId);

	Osoba updatePerson(Osoba osoba);

}
