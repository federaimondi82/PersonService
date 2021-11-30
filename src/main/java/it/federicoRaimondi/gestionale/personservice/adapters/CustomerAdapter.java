package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.CustomerView;
import it.federicoRaimondi.gestionale.personservice.builders.CustomerBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe CustomerView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class CustomerAdapter {

    private static CustomerAdapter instance;
    private CustomerView _instance;

    private CustomerAdapter(){}

    public static CustomerAdapter getInstance(){
	    synchronized ("CustomerAdapter") {
	        if(instance==null) {
	        	instance = new CustomerAdapter();
        	}
	        return instance;
        }
    }

    public CustomerAdapter adapt(CustomerView _instance){
        this._instance = _instance;
        return this;
    }

    public CustomerAdapter adapt(){
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
			if(this._instance.getPartitaIva() != null) { map.put("partitaIva",this._instance.getPartitaIva()); }
			if(this._instance.getRagioneSociale() != null) { map.put("ragioneSociale",this._instance.getRagioneSociale()); }
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public CustomerView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        CustomerBuilder _instance = new CustomerBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("name") && data.get("name") != null ) { _instance.setName((String)data.get("name")); }	
        	if(data.containsKey("surname") && data.get("surname") != null ) { _instance.setSurname((String)data.get("surname")); }	
        	if(data.containsKey("partitaIva") && data.get("partitaIva") != null ) { _instance.setPartitaIva((String)data.get("partitaIva")); }	
        	if(data.containsKey("ragioneSociale") && data.get("ragioneSociale") != null ) { _instance.setRagioneSociale((String)data.get("ragioneSociale")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter CustomerView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
