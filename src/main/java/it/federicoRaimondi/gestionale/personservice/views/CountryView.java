package it.federicoRaimondi.gestionale.personservice.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "country")
public class CountryView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Size( min=3, max=255)
	@Column(name="name")
	private String name;
	@Column(name="personID")
	private Long personID;
	@Column(name="stateID")
	private Long stateID;

	public CountryView() {
	}
	public CountryView(Long ID,String name,Long personID,Long stateID) {
		super();
		this.ID = ID;
		this.name = name;
		this.personID = personID;
		this.stateID = stateID;
	}
	
	public void setID(Long ID) {
		this.ID = ID;
	}
	public Long getID() {
		return this.ID ;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name ;
	}
	public void setPersonID(Long personID) {
		this.personID = personID;
	}
	public Long getPersonID() {
		return this.personID ;
	}
	public void setStateID(Long stateID) {
		this.stateID = stateID;
	}
	public Long getStateID() {
		return this.stateID ;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
