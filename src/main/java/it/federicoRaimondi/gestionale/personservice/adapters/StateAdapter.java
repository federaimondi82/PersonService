package it.federicoRaimondi.gestionale.personservice.adapters;

import java.util.HashMap;
import java.util.Map;
import it.federicoRaimondi.gestionale.personservice.views.StateView;
import it.federicoRaimondi.gestionale.personservice.builders.StateBuilder;

/**
 * Singleton e Adapter per i dati riguardate la classe StateView. Fornisce due metodi per trasformare
 * i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 * */
public class StateAdapter {

    private static StateAdapter instance;
    private StateView _instance;

    private StateAdapter(){}

    public static StateAdapter getInstance(){
	    synchronized ("StateAdapter") {
	        if(instance==null) {
	        	instance = new StateAdapter();
        	}
	        return instance;
        }
    }

    public StateAdapter adapt(StateView _instance){
        this._instance = _instance;
        return this;
    }

    public StateAdapter adapt(){
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
        
        return map;
    }

    /**
     * Trasformazione in oggetto di un json provenienente dal client o dal database
     * Deserializzazione del dato
     * @param data un oggetto chiave,valore con le informazioni dell'utente
     * @return un oggetto User
     * @see User
     * */
    public StateView toObject(Map<String, ?> data) {
    	if(data == null) {
    		throw new NullPointerException("data from client null");
    	}
        StateBuilder _instance = new StateBuilder();
        
        try {
        	if(data.containsKey("ID") && data.get("ID") != null ) { _instance.setID((Long)data.get("ID")); }	
        	if(data.containsKey("name") && data.get("name") != null ) { _instance.setName((String)data.get("name")); }	
			this._instance=_instance.build();
        }
        catch(Exception e) {
        	System.out.println("Errore adapter StateView");
        	System.out.println("data: "+data.toString());
        }
        
        return this._instance;
    }
    
}
