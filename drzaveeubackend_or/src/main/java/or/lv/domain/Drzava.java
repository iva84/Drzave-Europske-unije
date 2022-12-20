package or.lv.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Drzava {
	
	@Id
	@Column(nullable = false, 
			updatable = false, 
			length = 3
	)
	private String isoOznaka;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column(nullable = false)
	private String puniNaziv;
	
	@Column(nullable = false)
	private LocalDate datumUlaskaUEU;

	@Column(nullable = false)
	private Double povrsina;
	
	@Column(nullable = false)
	private Integer brojStanovnika;
	
	@Column(nullable = false)
	private String nazivHimne;
	
	@Column(nullable = false)
	private String glavniGrad;
	
	@OneToMany
	private Set<Jezik> sluzbeniJezik;
	
	@OneToMany
	private Set<Valuta> sluzbenaValuta;
	
	@OneToMany
	private Set<Osoba> drzavniVrh;

	public String getIsoOznaka() {
		return isoOznaka;
	}

	public void setIsoOznaka(String isoOznaka) {
		this.isoOznaka = isoOznaka;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPuniNaziv() {
		return puniNaziv;
	}

	public void setPuniNaziv(String puniNaziv) {
		this.puniNaziv = puniNaziv;
	}

	public LocalDate getDatumUlaskaUEU() {
		return datumUlaskaUEU;
	}

	public void setDatumUlaskaUEU(LocalDate datumUlaskaUEU) {
		this.datumUlaskaUEU = datumUlaskaUEU;
	}

	public Double getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(Double povrsina) {
		this.povrsina = povrsina;
	}

	public Integer getBrojStanovnika() {
		return brojStanovnika;
	}

	public void setBrojStanovnika(Integer brojStanovnika) {
		this.brojStanovnika = brojStanovnika;
	}

	public String getNazivHimne() {
		return nazivHimne;
	}

	public void setNazivHimne(String nazivHimne) {
		this.nazivHimne = nazivHimne;
	}

	public String getGlavniGrad() {
		return glavniGrad;
	}

	public void setGlavniGrad(String glavniGrad) {
		this.glavniGrad = glavniGrad;
	}

	public Set<Jezik> getSluzbeniJezik() {
		return sluzbeniJezik;
	}

	public void setSluzbeniJezik(Set<Jezik> sluzbeniJezik) {
		this.sluzbeniJezik = sluzbeniJezik;
	}

	public Set<Valuta> getSluzbenaValuta() {
		return sluzbenaValuta;
	}

	public void setSluzbenaValuta(Set<Valuta> sluzbenaValuta) {
		this.sluzbenaValuta = sluzbenaValuta;
	}

	public Set<Osoba> getDrzavniVrh() {
		return drzavniVrh;
	}

	public void setDrzavniVrh(Set<Osoba> drzavniVrh) {
		this.drzavniVrh = drzavniVrh;
	}

	@Override
	public String toString() {
		return "Drzava [isoOznaka=" + isoOznaka + ", naziv=" + naziv + ", puniNaziv=" + puniNaziv + ", datumUlaskaUEU="
				+ datumUlaskaUEU + ", povrsina=" + povrsina + ", brojStanovnika=" + brojStanovnika + ", nazivHimne="
				+ nazivHimne + ", glavniGrad=" + glavniGrad + ", sluzbeniJezik=" + sluzbeniJezik + ", sluzbenaValuta="
				+ sluzbenaValuta + ", drzavniVrh=" + drzavniVrh + "]";
	}
	
}
