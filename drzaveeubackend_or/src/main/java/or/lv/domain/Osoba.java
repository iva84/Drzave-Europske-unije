package or.lv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Osoba {
	
	@Id
	@Column(nullable = false, updatable = false)
	@SequenceGenerator(
		name = "osoba_id_sequence",
		sequenceName = "osoba_id_sequence",
		allocationSize = 1,
		initialValue = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "osoba_id_sequence"
	)
	private Integer osobaId;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(nullable = false)
	private String uloga;

	public Integer getOsobaId() {
		return osobaId;
	}

	public void setOsobaId(Integer osobaId) {
		this.osobaId = osobaId;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
}
