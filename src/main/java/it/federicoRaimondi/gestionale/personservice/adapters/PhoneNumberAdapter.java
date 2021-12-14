package it.federicoRaimondi.gestionale.personservice.adapters;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;

/**
 * Singleton e Adapter per i dati riguardate la classe PhoneNumberView. Fornisce due metodi per
 * trasformare i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per
 * poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 */
public class PhoneNumberAdapter {

	private static PhoneNumberAdapter instance;
	private PhoneNumberView _instance;

	private PhoneNumberAdapter() {
	}

	public static PhoneNumberAdapter getInstance() {
		synchronized ("PhoneNumberAdapter") {
			if (instance == null) {
				instance = new PhoneNumberAdapter();
			}
			return instance;
		}
	}

	public PhoneNumberAdapter adapt(PhoneNumberView _instance) {
		this._instance = _instance;
		return this;
	}

	public PhoneNumberAdapter adapt() {
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
	public PhoneNumberView toObject(String json) {
		if (json == null) {
			throw new NullPointerException("json from client null");
		}

		return new Gson().fromJson(json, PhoneNumberView.class);
	}

}