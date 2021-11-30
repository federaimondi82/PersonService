package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.CountryView;
import it.federicoRaimondi.gestionale.personservice.builders.CountryBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe CountryView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class CountryAdapter {

    private static CountryAdapter instance;
    private CountryView _instance;

    private CountryAdapter(){}

    public static CountryAdapter getInstance(){
	    synchronized ("CountryAdapter") {
	        if(instance==null) {
	        	instance = new CountryAdapter();
        	}
	        return instance;
        }
    }

    public CountryAdapter adapt(CountryView _instance){
        this._instance = _instance;
        return this;
    }

    public CountryAdapter adapt(){
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
			if(this._instance.getPersonID() != null) { map.put("personID",this._instance.getPersonID()); }
			if(this._instance.getStateID() != null) { map.put("stateID",this._instance.getStateID()); }
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public CountryView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        CountryBuilder _instance = new CountryBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("name") && data.get("name") != null ) { _instance.setName((String)data.get("name")); }	
        	if(data.containsKey("personID") && data.get("personID") != null ) { _instance.setPersonID((Long)data.get("personID")); }	
        	if(data.containsKey("stateID") && data.get("stateID") != null ) { _instance.setStateID((Long)data.get("stateID")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter CountryView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
