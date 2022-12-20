package or.lv.rest;

import java.time.LocalDate;
import java.util.Set;

import or.lv.domain.Jezik;
import or.lv.domain.Osoba;
import or.lv.domain.Valuta;

public class DrzavaUpdateDTO {
	
	private String naziv;
	private String puniNaziv;
	private LocalDate datumUlaskaUEU;
	private Double povrsina;
	private Integer brojStanovnika;
	private String nazivHimne;
	private String glavniGrad;
	private Set<Jezik> sluzbeniJezik;
	private Set<Valuta> sluzbenaValuta;
	private Set<Osoba> drzavniVrh;
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
	
	
}
