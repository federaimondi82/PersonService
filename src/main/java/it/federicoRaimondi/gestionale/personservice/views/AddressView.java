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
@Table(name = "address")
public class AddressView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Size( min=3, max=255)
	@Column(name="address")
	private String address;
	@Column(name="number")
	private Integer number;
	@Column(name="personID")
	private Long personID;

	public AddressView() {
	}
	public AddressView(Long ID,String address,Integer number,Long personID) {
		super();
		this.ID = ID;
		this.address = address;
		this.number = number;
		this.personID = personID;
	}
	
	public void setID(Long ID) {
		this.ID = ID;
	}
	public Long getID() {
		return this.ID ;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address ;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getNumber() {
		return this.number ;
	}
	public void setPersonID(Long personID) {
		this.personID = personID;
	}
	public Long getPersonID() {
		return this.personID ;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
