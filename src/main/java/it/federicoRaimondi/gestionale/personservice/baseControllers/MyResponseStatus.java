package it.federicoRaimondi.gestionale.personservice.baseControllers;

/**
 * Enum di appoggio per le risposte verso il client
 * 
 * @author Federico Raimondi
 *
 */
public enum MyResponseStatus
{

	OK				(0, "Ok"), 
	BAD				(1, "Bad Request"), 
	UNAUTHORIZED	(2, "Non autorizzato"),
	INTERNAL		(3, "Errore inyerno del server"),
	NO_CONTENT		(4, "Nessun contenuto trovato"),;

	private int code;
	private String name;

	MyResponseStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName(MyResponseStatus status) {
		for (MyResponseStatus s : values()) {
			if (s.equals(status)) {
				return s.name;
			}
		}
		return "";
	}

	public int getCode(MyResponseStatus status) {
		for (MyResponseStatus s : values()) {
			if (s.equals(status)) {
				return s.code;
			}
		}
		return -1;
	}

}
