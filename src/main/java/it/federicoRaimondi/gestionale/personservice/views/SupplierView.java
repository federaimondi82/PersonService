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
@Table(name = "supplier")
public class SupplierView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Column(name="name",length=50)
	private String name;
	@Column(name="surname",length=50)
	private String surname;
	@Column(name="partitaIva",length=50)
	private String partitaIva;
	@Column(name="ragionaSociale",length=50)
	private String ragionaSociale;

	public SupplierView() {
	}
	public SupplierView(Long ID,String name,String surname,String partitaIva,String ragionaSociale) {
		super();
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.partitaIva = partitaIva;
		this.ragionaSociale = ragionaSociale;
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
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurname() {
		return this.surname ;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getPartitaIva() {
		return this.partitaIva ;
	}
	public void setRagionaSociale(String ragionaSociale) {
		this.ragionaSociale = ragionaSociale;
	}
	public String getRagionaSociale() {
		return this.ragionaSociale ;
	}

	@Override
    public String toString() {
        return new  GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
