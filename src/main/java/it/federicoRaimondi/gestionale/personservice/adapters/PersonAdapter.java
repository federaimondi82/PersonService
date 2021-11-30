package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.PersonView;
import it.federicoRaimondi.gestionale.personservice.builders.PersonBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe PersonView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class PersonAdapter {

    private static PersonAdapter instance;
    private PersonView _instance;

    private PersonAdapter(){}

    public static PersonAdapter getInstance(){
	    synchronized ("PersonAdapter") {
	        if(instance==null) {
	        	instance = new PersonAdapter();
        	}
	        return instance;
        }
    }

    public PersonAdapter adapt(PersonView _instance){
        this._instance = _instance;
        return this;
    }

    public PersonAdapter adapt(){
        return this;
    }

    /**
     * Trasforma una istanza della businesslogic in formato json
     * @return
     */
    public Map<String, Object> toJson(){
        Map<String,Object> map=new HashMap<String,Object>();
        
			if(this._instance.getID() != null) { map.put("ID",this._instance.getID()); }
			if(this._instance.getName() != null) { map.put("name",this._instance.getName()); }
			if(this._instance.getSurname() != null) { map.put("surname",this._instance.getSurname()); }
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public PersonView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        PersonBuilder _instance = new PersonBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("name") && data.get("name") != null ) { _instance.setName((String)data.get("name")); }	
        	if(data.containsKey("surname") && data.get("surname") != null ) { _instance.setSurname((String)data.get("surname")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter PersonView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
