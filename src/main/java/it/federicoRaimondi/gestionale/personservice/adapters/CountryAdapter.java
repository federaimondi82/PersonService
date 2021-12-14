package it.federicoRaimondi.gestionale.personservice.adapters;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.views.CountryView;

/**
 * Singleton e Adapter per i dati riguardate la classe CountryView. Fornisce due metodi per
 * trasformare i dati in Json per inviarli nella rete oppure trasformare un json in oggetto per
 * poterlo poi gestire nell'applicazione
 * 
 * @author Raimondi Federico
 */
public class CountryAdapter {

	private static CountryAdapter instance;
	private CountryView _instance;

	private CountryAdapter() {
	}

	public static CountryAdapter getInstance() {
		synchronized ("CountryAdapter") {
			if (instance == null) {
				instance = new CountryAdapter();
			}
			return instance;
		}
	}

	public CountryAdapter adapt(CountryView _instance) {
		this._instance = _instance;
		return this;
	}

	public CountryAdapter adapt() {
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
	public CountryView toObject(String json) {
		if (json == null) {
			throw new NullPointerException("json from client null");
		}

		return new Gson().fromJson(json, CountryView.class);
	}

}