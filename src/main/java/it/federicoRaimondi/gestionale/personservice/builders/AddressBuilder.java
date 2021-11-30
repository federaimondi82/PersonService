package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.AddressView;

public class AddressBuilder {

	private Long ID;
	private String address;
	private Integer number;
	private Long personID;

	public AddressBuilder() {
	}
	
	public AddressBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public AddressBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	public AddressBuilder setNumber(Integer number) {
		this.number = number;
		return this;
	}
	public AddressBuilder setPersonID(Long personID) {
		this.personID = personID;
		return this;
	}

	public AddressView build() {
		AddressView p = new AddressView(ID,address,number,personID);
		return p;
	}

}
