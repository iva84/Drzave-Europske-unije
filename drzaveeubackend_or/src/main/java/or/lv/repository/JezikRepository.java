package or.lv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.lv.domain.Jezik;

@Repository
public interface JezikRepository extends JpaRepository<Jezik, String> {

}
