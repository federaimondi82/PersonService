package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.CustomerView;

public class CustomerBuilder {

	private Long ID;
	private String name;
	private String surname;
	private String partitaIva;
	private String ragioneSociale;

	public CustomerBuilder() {
	}
	
	public CustomerBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public CustomerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public CustomerBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public CustomerBuilder setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
		return this;
	}
	public CustomerBuilder setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
		return this;
	}

	public CustomerView build() {
		CustomerView p = new CustomerView(ID,name,surname,partitaIva,ragioneSociale);
		return p;
	}

}
