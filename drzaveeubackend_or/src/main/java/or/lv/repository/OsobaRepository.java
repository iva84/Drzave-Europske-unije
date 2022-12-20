package or.lv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.lv.domain.Osoba;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Integer> {

}
