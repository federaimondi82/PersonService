package it.federicoRaimondi.gestionale.personservice.adapters;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.views.SupplierView;

/**
 * Singleton e Adapter per i dati riguardate la classe SupplierView. Fornisce due metodi per
 * trasformare i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per
 * poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 */
public class SupplierAdapter {

	private static SupplierAdapter instance;
	private SupplierView _instance;

	private SupplierAdapter() {
	}

	public static SupplierAdapter getInstance() {
		synchronized ("SupplierAdapter") {
			if (instance == null) {
				instance = new SupplierAdapter();
			}
			return instance;
		}
	}

	public SupplierAdapter adapt(SupplierView _instance) {
		this._instance = _instance;
		return this;
	}

	public SupplierAdapter adapt() {
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
	public SupplierView toObject(String json) {
		if (json == null) {
			throw new NullPointerException("json from client null");
		}

		return new Gson().fromJson(json, SupplierView.class);
	}

}