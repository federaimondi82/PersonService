package it.federicoRaimondi.gestionale.personservice.adapters;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.views.CustomerView;

/**
 * Singleton e Adapter per i dati riguardate la classe CustomerView. Fornisce due metodi per
 * trasformare i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per
 * poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 */
public class CustomerAdapter {

	private static CustomerAdapter instance;
	private CustomerView _instance;

	private CustomerAdapter() {
	}

	public static CustomerAdapter getInstance() {
		synchronized ("CustomerAdapter") {
			if (instance == null) {
				instance = new CustomerAdapter();
			}
			return instance;
		}
	}

	public CustomerAdapter adapt(CustomerView _instance) {
		this._instance = _instance;
		return this;
	}

	public CustomerAdapter adapt() {
		return this;
	}

	/**
	 * Trasforma una istanza della businesslogic in formato json
	 * 
	 * @return
	 */
	public String toJson() {
		return new Gson().toJson(_instance);
	}

	/**
	 * Trasformazione in oggetto di un json provenienente dal client o dal database Deserializzazione
	 * del dato
	 * 
	 * @param  data un oggetto chiave,valore con le informazioni dell'utente
	 * @return      un oggetto User
	 * @see         User
	 */
	public CustomerView toObject(String json) {
		if (json == null) {
			throw new NullPointerException("json from client null");
		}

		return new Gson().fromJson(json, CustomerView.class);
	}

}