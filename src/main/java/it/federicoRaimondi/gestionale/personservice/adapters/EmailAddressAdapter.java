package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;
import it.federicoRaimondi.gestionale.personservice.builders.EmailAddressBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe EmailAddressView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class EmailAddressAdapter {

    private static EmailAddressAdapter instance;
    private EmailAddressView _instance;

    private EmailAddressAdapter(){}

    public static EmailAddressAdapter getInstance(){
	    synchronized ("EmailAddressAdapter") {
	        if(instance==null) {
	        	instance = new EmailAddressAdapter();
        	}
	        return instance;
        }
    }

    public EmailAddressAdapter adapt(EmailAddressView _instance){
        this._instance = _instance;
        return this;
    }

    public EmailAddressAdapter adapt(){
        return this;
    }

    /**
     * Trasforma una istanza della businesslogic in formato json
     * @return
     */
    public Map<String, Object> toJson(){
        Map<String,Object> map=new HashMap<String,Object>();
        
			if(this._instance.getID() != null) { map.put("ID",this._instance.getID()); }
			if(this._instance.getEmail() != null) { map.put("email",this._instance.getEmail()); }
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
    public EmailAddressView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        EmailAddressBuilder _instance = new EmailAddressBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("email") && data.get("email") != null ) { _instance.setEmail((String)data.get("email")); }	
        	if(data.containsKey("personID") && data.get("personID") != null ) { _instance.setPersonID((Long)data.get("personID")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter EmailAddressView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
