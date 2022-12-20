package or.lv.service;

import java.util.Optional;

import or.lv.domain.Jezik;

public interface JezikService {

	Optional<Jezik> findById(String id);

	Jezik createLanguage(Jezik jezik);
	//void createLanguage(Jezik jezik);

	Optional<Jezik> getById(String isoOzn);

	Jezik updateLanguage(Jezik jezik);

}
