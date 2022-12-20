package or.lv.rest;

import java.time.LocalDate;

public class DrzavaFilterDTO {
	
	// moramo osigurati na frontendu da se uvijek salje vrijednost
	private Boolean najopcenitijiFilter;

	private String isoOznaka;
	private String naziv;
	private String puniNaziv;
	private LocalDate datumUlaskaUEU;
	private Double povrsina;
	private Integer brojStanovnika;
	private String nazivHimne;
	private String glavniGrad;
	private String sluzbeniJezikISOozn;
	private String sluzbeniJezikNaziv;
	private String sluzbenaValutaISOozn;
	private String sluzbenaValutaNaziv;
	private String drzavniVrhIme;
	private String drzavniVrhPrezime;
	private String drzavniVrhUloga;
	
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
	public String getSluzbeniJezikISOozn() {
		return sluzbeniJezikISOozn;
	}
	public void setSluzbeniJezikISOozn(String sluzbeniJezikISOozn) {
		this.sluzbeniJezikISOozn = sluzbeniJezikISOozn;
	}
	public String getSluzbeniJezikNaziv() {
		return sluzbeniJezikNaziv;
	}
	public void setSluzbeniJezikNaziv(String sluzbeniJezikNaziv) {
		this.sluzbeniJezikNaziv = sluzbeniJezikNaziv;
	}
	public String getSluzbenaValutaISOozn() {
		return sluzbenaValutaISOozn;
	}
	public void setSluzbenaValutaISOozn(String sluzbenaValutaISOozn) {
		this.sluzbenaValutaISOozn = sluzbenaValutaISOozn;
	}
	public String getSluzbenaValutaNaziv() {
		return sluzbenaValutaNaziv;
	}
	public void setSluzbenaValutaNaziv(String sluzbenaValutaNaziv) {
		this.sluzbenaValutaNaziv = sluzbenaValutaNaziv;
	}
	public String getDrzavniVrhIme() {
		return drzavniVrhIme;
	}
	public void setDrzavniVrhIme(String drzavniVrhIme) {
		this.drzavniVrhIme = drzavniVrhIme;
	}
	public String getDrzavniVrhPrezime() {
		return drzavniVrhPrezime;
	}
	public void setDrzavniVrhPrezime(String drzavniVrhPrezime) {
		this.drzavniVrhPrezime = drzavniVrhPrezime;
	}
	public String getDrzavniVrhUloga() {
		return drzavniVrhUloga;
	}
	public void setDrzavniVrhUloga(String drzavniVrhUloga) {
		this.drzavniVrhUloga = drzavniVrhUloga;
	}
	public Boolean isNajopcenitijiFilter() {
		return najopcenitijiFilter;
	}
	public void setNajopcenitijiFilter(Boolean najopcenitijiFilter) {
		this.najopcenitijiFilter = najopcenitijiFilter;
	}
	@Override
	public String toString() {
		return "DrzavaFilterDTO [najopcenitijiFilter=" + najopcenitijiFilter + ", isoOznaka=" + isoOznaka + ", naziv="
				+ naziv + ", puniNaziv=" + puniNaziv + ", datumUlaskaUEU=" + datumUlaskaUEU + ", povrsina=" + povrsina
				+ ", brojStanovnika=" + brojStanovnika + ", nazivHimne=" + nazivHimne + ", glavniGrad=" + glavniGrad
				+ ", sluzbeniJezikISOozn=" + sluzbeniJezikISOozn + ", sluzbeniJezikNaziv=" + sluzbeniJezikNaziv
				+ ", sluzbenaValutaISOozn=" + sluzbenaValutaISOozn + ", sluzbenaValutaNaziv=" + sluzbenaValutaNaziv
				+ ", drzavniVrhIme=" + drzavniVrhIme + ", drzavniVrhPrezime=" + drzavniVrhPrezime + ", drzavniVrhUloga="
				+ drzavniVrhUloga + "]";
	}
	
	
	
}
