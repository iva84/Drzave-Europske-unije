package or.lv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Jezik {
	
	@Id
	@Column(nullable = false, updatable = false, length = 2)
	private String isoOzn;
	
	@Column(nullable = false)
	private String naziv;

	public String getIsoOzn() {
		return isoOzn;
	}

	public void setIsoOzn(String isoOzn) {
		this.isoOzn = isoOzn;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	@Override
	public String toString() {
		return "{\n\tisoOzn:" + isoOzn + ",\n\tnaziv:" + naziv + "}";
	}
	
}
