package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.SupplierView;

public class SupplierBuilder {

	private Long ID;
	private String name;
	private String surname;
	private String partitaIva;
	private String ragionaSociale;

	public SupplierBuilder() {
	}
	
	public SupplierBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public SupplierBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public SupplierBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public SupplierBuilder setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
		return this;
	}
	public SupplierBuilder setRagionaSociale(String ragionaSociale) {
		this.ragionaSociale = ragionaSociale;
		return this;
	}

	public SupplierView build() {
		SupplierView p = new SupplierView(ID,name,surname,partitaIva,ragionaSociale);
		return p;
	}

}
