package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.CountryView;

public class CountryBuilder {

	private Long ID;
	private String name;
	private Long personID;
	private Long stateID;

	public CountryBuilder() {
	}
	
	public CountryBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public CountryBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public CountryBuilder setPersonID(Long personID) {
		this.personID = personID;
		return this;
	}
	public CountryBuilder setStateID(Long stateID) {
		this.stateID = stateID;
		return this;
	}

	public CountryView build() {
		CountryView p = new CountryView(ID,name,personID,stateID);
		return p;
	}

}
