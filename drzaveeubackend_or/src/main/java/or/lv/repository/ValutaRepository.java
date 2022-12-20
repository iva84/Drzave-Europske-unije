package or.lv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.lv.domain.Valuta;

@Repository
public interface ValutaRepository extends JpaRepository<Valuta, String> {

}
