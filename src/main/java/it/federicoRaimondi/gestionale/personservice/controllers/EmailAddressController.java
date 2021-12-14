package it.federicoRaimondi.gestionale.personservice.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.federicoRaimondi.gestionale.personservice.adapters.EmailAddressAdapter;
import it.federicoRaimondi.gestionale.personservice.baseControllers.BaseController;
import it.federicoRaimondi.gestionale.personservice.baseControllers.MyResponseStatus;
import it.federicoRaimondi.gestionale.personservice.daoServices.EmailAddressDAO;
import it.federicoRaimondi.gestionale.personservice.services.EmailAddressService;
import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;

@RestController
@RequestMapping("personservice/emailaddress")
@Api(value = "PersonService", tags = "Controller per le chiamate su PersonService")
public class EmailAddressController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(EmailAddressController.class.getName());
	private EmailAddressService service;

	@Autowired
	private EmailAddressDAO repository;

	public EmailAddressService getService() {
		service = new EmailAddressService(repository);
		return service;
	}

	/**
	 * gateway/personservice/emailaddress/store Memorizzazione di una risorsa EmailAddressView sul
	 * database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Salvataggio di EmailAddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("store")
	public ResponseEntity<?> store(EmailAddressView instance) {
		// Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);

		return getResponse(ID, "Salvataggio InvoiceView terminato");
	}

	/**
	 * <p>
	 * gateway/invoiceservice/emailaddress/storeJson <br>
	 * Memorizzazione di una risorsa EmailAddressView sul database.
	 * </p>
	 */
	@ApiOperation(value = "Salvataggio di EmailAddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("storeJson")
	public ResponseEntity<?> storeJson(@RequestBody String instance) {
		if (instance == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene salvata l'instanca sul DB
		Long ID = getService().store(EmailAddressAdapter.getInstance().toObject(instance));
		if (ID == null)
			return getResponse(MyResponseStatus.INTERNAL, "Errore interno");
		return getResponse(ID, "Salvataggio EmailAddressView terminato");
	}

	/**
	 * gateway/personservice/emailaddress/update Aggiorna una risorsa EmailAddressView sul database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Aggiornamento di EmailAddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Aggiornamento corretto"),
			@ApiResponse(code = 500, message = "Aggiornamento non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PutMapping("update")
	public ResponseEntity<?> update(EmailAddressView instance) {
		if (instance.getID() == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene aggiornata la risorsa l'instanca sul DB
		Boolean isUpdated = getService().update(instance);

		MyResponseStatus response = null;
		if (isUpdated == false)
			response = MyResponseStatus.NO_CONTENT;
		else
			response = MyResponseStatus.OK;
		return getResponseWithStatus(response, "Aggiornamento EmailAddressView terminato");
	}

	/**
	 * gateway/personservice/emailaddress/delete Cancellazione di una risorsa EmailAddressView sul
	 * database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Cancellazione di EmailAddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cancellazione effettuata con successo"),
			@ApiResponse(code = 500, message = "Cancellazione effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(EmailAddressView instance) {
		if (instance.getID() == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		Boolean isDeleted = getService().delete(instance);

		MyResponseStatus response = null;
		if (isDeleted == true)
			response = MyResponseStatus.OK;
		else
			response = MyResponseStatus.BAD;
		return getResponseWithStatus(response, "Cancellazione EmailAddressView terminato");

	}

	/**
	 * gateway/personservice/emailaddress/delete/{ID} Cancellazione di una risorsa EmailAddressView sul
	 * database.
	 * 
	 * @param ID la primary key dell'istanza da cancellare
	 */
	@ApiOperation(value = "Cancellazione di EmailAddressView dato il suo ID", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cancellazione effettuata con successo"),
			@ApiResponse(code = 500, message = "Cancellazione effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@DeleteMapping("delete/{ID}")
	public ResponseEntity<?> deleteByID(@PathVariable("ID") Long ID) {
		if (ID == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		Boolean isDeleted = getService().deleteByID(ID);

		MyResponseStatus response = null;
		if (isDeleted == true)
			response = MyResponseStatus.OK;
		else
			response = MyResponseStatus.BAD;
		return getResponseWithStatus(response, "Cancellazione EmailAddressView con ID=" + ID + " terminato");
	}

	/**
	 * gateway/personservice/emailaddress/get/{ID} Richiesta di una risorsa EmailAddressView dal
	 * database.
	 * 
	 * @param ID la primary key dell'istanza che si vuole ottenere
	 */
	@ApiOperation(value = "Restituzione risorsa EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("get/{ID}")
	public ResponseEntity<?> get(@PathVariable("ID") Long ID) {
		if (ID == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		return getResponse(getService().findByID(ID), "Richiesta di EmailAddressView terminata");
	}

	/**
	 * gateway/personservice/emailaddress/getAll Richiesta di tutte le risorse EmailAddressView dal
	 * database.
	 */
	@ApiOperation(value = "Restituzione i tutte le risorse EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return getResponse(getService().findAll(), "Richiesta di EmailAddressView terminata");
	}

	/**
	 * <p>
	 * gateway/invoiceservice/emailaddress/getEmailAddressByID <br>
	 * Restituzione di una risorsa in formato Json di EmailAddress
	 * </p>
	 */
	@ApiOperation(value = "Restituzione di una risorsa in formato Json di EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping(value = "getEmailAddressByID/{ID}")
	public String getEmailAddressByID(@PathVariable("ID") Long ID) {
		if (ID == null)
			return null;
		return getService().findByIdToJson(ID);
	}

	/*****************************************************************/
	/*************************** FRONT_END *****************************/
	/*****************************************************************/

	/**
	 * gateway/personservice/emailaddress/view/{ID} Richiesta di una risorsa EmailAddressView dal
	 * database.
	 * 
	 * @param ID la primary key dell'istanza che si vuole visualizzare
	 */
	@ApiOperation(value = "Vista frontend di EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("view/{ID}")
	public ModelAndView view(@PathVariable("ID") Long ID) {
		if (ID == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		return getService().view(ID);
	}

	/**
	 * gateway/personservice/emailaddress/input Richiesta della pagina di inserimento dati di
	 * EmailAddressView
	 */
	@ApiOperation(value = "Input da frontend di EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("input")
	public ModelAndView input() {
		return getService().input();
	}

	/**
	 * gateway/personservice/emailaddress/search Richiesta della pagina di ricerca dati di
	 * EmailAddressView
	 */
	@ApiOperation(value = "Ricerca frontend di EmailAddress", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("search")
	public ModelAndView search() {
		// TODO
		// return getService().search();
		return null;
	}

	/**
	 * gateway/personservice/emailaddress/storeView Memorizzazione di una risorsa EmailAddressView sul
	 * database.
	 * 
	 * @param instance l'oggetto passato dal frontend
	 */
	@ApiOperation(value = "Salvataggio da frontend di EmailAddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("storeView")
	public ModelAndView storeView(EmailAddressView instance) {
		// if(instance.getID()==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);
		if (ID == null)
			return null;
		return getService().view(ID);
	}

}
