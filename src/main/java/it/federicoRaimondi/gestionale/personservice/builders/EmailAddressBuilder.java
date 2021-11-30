package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;

public class EmailAddressBuilder {

	private Long ID;
	private String email;
	private Long personID;

	public EmailAddressBuilder() {
	}
	
	public EmailAddressBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public EmailAddressBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	public EmailAddressBuilder setPersonID(Long personID) {
		this.personID = personID;
		return this;
	}

	public EmailAddressView build() {
		EmailAddressView p = new EmailAddressView(ID,email,personID);
		return p;
	}

}
