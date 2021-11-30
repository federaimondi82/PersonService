package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;

public class PhoneNumberBuilder {

	private Long ID;
	private String number;
	private Integer type;
	private Long personID;

	public PhoneNumberBuilder() {
	}
	
	public PhoneNumberBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public PhoneNumberBuilder setNumber(String number) {
		this.number = number;
		return this;
	}
	public PhoneNumberBuilder setType(Integer type) {
		this.type = type;
		return this;
	}
	public PhoneNumberBuilder setPersonID(Long personID) {
		this.personID = personID;
		return this;
	}

	public PhoneNumberView build() {
		PhoneNumberView p = new PhoneNumberView(ID,number,type,personID);
		return p;
	}

}
