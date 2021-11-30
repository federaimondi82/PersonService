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
@Table(name = "emailaddress")
public class EmailAddressView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Size( min=3, max=255)
	@Column(name="email")
	private String email;
	@Column(name="personID")
	private Long personID;

	public EmailAddressView() {
	}
	public EmailAddressView(Long ID,String email,Long personID) {
		super();
		this.ID = ID;
		this.email = email;
		this.personID = personID;
	}
	
	public void setID(Long ID) {
		this.ID = ID;
	}
	public Long getID() {
		return this.ID ;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email ;
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
