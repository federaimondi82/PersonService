package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.PersonView;

public class PersonBuilder {

	private Long ID;
	private String name;
	private String surname;

	public PersonBuilder() {
	}
	
	public PersonBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public PersonBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public PersonBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public PersonView build() {
		PersonView p = new PersonView(ID,name,surname);
		return p;
	}

}
