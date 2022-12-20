package or.lv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import or.lv.domain.Drzava;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, String> {

	@Query("SELECT d FROM Drzava d WHERE LOWER(d.naziv) LIKE %:naziv%")
	List<Drzava> findByNameContaining(@Param("naziv") String dioNaziva);

	@Query("SELECT d FROM Drzava d WHERE LOWER(d.puniNaziv) LIKE %:puniNaziv%")
	List<Drzava> findByFullNameContaining(@Param("puniNaziv") String lowerCase);

	@Query("SELECT d FROM Drzava d WHERE LOWER(d.isoOznaka) LIKE %:isoOznaka%")
	List<Drzava> findByISOContaining(@Param("isoOznaka") String isoOznaka);

	@Query("SELECT d FROM Drzava d WHERE LOWER(CAST(d.datumUlaskaUEU AS string)) LIKE %:datumUlaskaUEU%")
	List<Drzava> findByEUDateContaining(@Param("datumUlaskaUEU") String datumUlaskaUEU);

	@Query("SELECT d FROM Drzava d WHERE LOWER(CAST(d.povrsina AS string)) LIKE %:povrsina%")
	List<Drzava> findBySurfaceContaining(String povrsina);

	@Query("SELECT d FROM Drzava d WHERE LOWER(CAST(d.brojStanovnika AS string)) LIKE %:brojStanovnika%")
	List<Drzava> findByPopulationContaining(String brojStanovnika);

	@Query("SELECT d FROM Drzava d WHERE LOWER(CAST(d.nazivHimne AS string)) LIKE %:nazivHimne%")
	List<Drzava> findByAnthemContaining(String nazivHimne);
	
	@Query("SELECT d FROM Drzava d WHERE LOWER(CAST(d.glavniGrad AS string)) LIKE %:glavniGrad%")
	List<Drzava> findByCapitalCityContaining(String glavniGrad);

}
