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
import com.google.gson.GsonBuilder;


@Entity
@Table(name = "phonenumber")
public class PhoneNumberView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Column(name="number",length=50)
	private String number;
	@Column(name="type")
	private Integer type;
	@Column(name="personID")
	private Long personID;

	public PhoneNumberView() {
	}
	public PhoneNumberView(Long ID,String number,Integer type,Long personID) {
		super();
		this.ID = ID;
		this.number = number;
		this.type = type;
		this.personID = personID;
	}
	
	public void setID(Long ID) {
		this.ID = ID;
	}
	public Long getID() {
		return this.ID ;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return this.number ;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getType() {
		return this.type ;
	}
	public void setPersonID(Long personID) {
		this.personID = personID;
	}
	public Long getPersonID() {
		return this.personID ;
	}

	@Override
    public String toString() {
        return new  GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
