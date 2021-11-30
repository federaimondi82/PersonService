package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;
import it.federicoRaimondi.gestionale.personservice.builders.PhoneNumberBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe PhoneNumberView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class PhoneNumberAdapter {

    private static PhoneNumberAdapter instance;
    private PhoneNumberView _instance;

    private PhoneNumberAdapter(){}

    public static PhoneNumberAdapter getInstance(){
	    synchronized ("PhoneNumberAdapter") {
	        if(instance==null) {
	        	instance = new PhoneNumberAdapter();
        	}
	        return instance;
        }
    }

    public PhoneNumberAdapter adapt(PhoneNumberView _instance){
        this._instance = _instance;
        return this;
    }

    public PhoneNumberAdapter adapt(){
        return this;
    }

    /**
     * Trasforma una istanza della businesslogic in formato json
     * @return
     */
    public Map<String, Object> toJson(){
        Map<String,Object> map=new HashMap<String,Object>();
        
			if(this._instance.getID() != null) { map.put("ID",this._instance.getID()); }
			if(this._instance.getNumber() != null) { map.put("number",this._instance.getNumber()); }
			if(this._instance.getType() != null) { map.put("type",this._instance.getType()); }
			if(this._instance.getPersonID() != null) { map.put("personID",this._instance.getPersonID()); }
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public PhoneNumberView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        PhoneNumberBuilder _instance = new PhoneNumberBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("number") && data.get("number") != null ) { _instance.setNumber((String)data.get("number")); }	
        	if(data.containsKey("type") && data.get("type") != null ) { _instance.setType((Integer)data.get("type")); }	
        	if(data.containsKey("personID") && data.get("personID") != null ) { _instance.setPersonID((Long)data.get("personID")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter PhoneNumberView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
