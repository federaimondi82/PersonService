package it.federicoRaimondi.gestionale.personservice.adapters;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;

/**
 * Singleton e Adapter per i dati riguardate la classe EmailAddressView. Fornisce due metodi per
 * trasformare i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per
 * poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 */
public class EmailAddressAdapter {

	private static EmailAddressAdapter instance;
	private EmailAddressView _instance;

	private EmailAddressAdapter() {
	}

	public static EmailAddressAdapter getInstance() {
		synchronized ("EmailAddressAdapter") {
			if (instance == null) {
				instance = new EmailAddressAdapter();
			}
			return instance;
		}
	}

	public EmailAddressAdapter adapt(EmailAddressView _instance) {
		this._instance = _instance;
		return this;
	}

	public EmailAddressAdapter adapt() {
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
	public EmailAddressView toObject(String json) {
		if (json == null) {
			throw new NullPointerException("json from client null");
		}

		return new Gson().fromJson(json, EmailAddressView.class);
	}

}