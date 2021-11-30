package it.federicoRaimondi.gestionale.personservice.builders;

import it.federicoRaimondi.gestionale.personservice.views.StateView;

public class StateBuilder {

	private Long ID;
	private String name;

	public StateBuilder() {
	}
	
	public StateBuilder setID(Long ID) {
		this.ID = ID;
		return this;
	}
	public StateBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public StateView build() {
		StateView p = new StateView(ID,name);
		return p;
	}

}
