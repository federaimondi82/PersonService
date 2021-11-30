package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.SupplierView;
import it.federicoRaimondi.gestionale.personservice.builders.SupplierBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe SupplierView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class SupplierAdapter {

    private static SupplierAdapter instance;
    private SupplierView _instance;

    private SupplierAdapter(){}

    public static SupplierAdapter getInstance(){
	    synchronized ("SupplierAdapter") {
	        if(instance==null) {
	        	instance = new SupplierAdapter();
        	}
	        return instance;
        }
    }

    public SupplierAdapter adapt(SupplierView _instance){
        this._instance = _instance;
        return this;
    }

    public SupplierAdapter adapt(){
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
			if(this._instance.getRagionaSociale() != null) { map.put("ragionaSociale",this._instance.getRagionaSociale()); }
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public SupplierView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        SupplierBuilder _instance = new SupplierBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("name") && data.get("name") != null ) { _instance.setName((String)data.get("name")); }	
        	if(data.containsKey("surname") && data.get("surname") != null ) { _instance.setSurname((String)data.get("surname")); }	
        	if(data.containsKey("partitaIva") && data.get("partitaIva") != null ) { _instance.setPartitaIva((String)data.get("partitaIva")); }	
        	if(data.containsKey("ragionaSociale") && data.get("ragionaSociale") != null ) { _instance.setRagionaSociale((String)data.get("ragionaSociale")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter SupplierView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
