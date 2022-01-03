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
@Table(name = "state")
public class StateView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long ID;
	@Column(name="name",length=50)
	private String name;

	public StateView() {
	}
	public StateView(Long ID,String name) {
		super();
		this.ID = ID;
		this.name = name;
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

	@Override
    public String toString() {
        return new  GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
