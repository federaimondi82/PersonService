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
import it.federicoRaimondi.gestionale.personservice.adapters.AddressAdapter;
import it.federicoRaimondi.gestionale.personservice.baseControllers.BaseController;
import it.federicoRaimondi.gestionale.personservice.baseControllers.MyResponseStatus;
import it.federicoRaimondi.gestionale.personservice.daoServices.AddressDAO;
import it.federicoRaimondi.gestionale.personservice.services.AddressService;
import it.federicoRaimondi.gestionale.personservice.views.AddressView;

@RestController
@RequestMapping("address")
@Api(value = "PersonService", tags = "Controller per le chiamate su PersonService")
public class AddressController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AddressController.class.getName());
	private AddressService service;

	@Autowired
	private AddressDAO repository;

	public AddressService getService() {
		service = new AddressService(repository);
		return service;
	}

	/**
	 * gateway/personservice/address/store Memorizzazione di una risorsa AddressView sul database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Salvataggio di AddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("store")
	public ResponseEntity<?> store(AddressView instance) {
		// Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);

		return getResponse(ID, "Salvataggio InvoiceView terminato");
	}

	/**
	 * <p>
	 * gateway/invoiceservice/address/storeJson <br>
	 * Memorizzazione di una risorsa AddressView sul database.
	 * </p>
	 */
	@ApiOperation(value = "Salvataggio di AddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("storeJson")
	public ResponseEntity<?> storeJson(@RequestBody String instance) {
		if (instance == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene salvata l'instanca sul DB
		Long ID = getService().store(AddressAdapter.getInstance().toObject(instance));
		if (ID == null)
			return getResponse(MyResponseStatus.INTERNAL, "Errore interno");
		return getResponse(ID, "Salvataggio AddressView terminato");
	}

	/**
	 * gateway/personservice/address/update Aggiorna una risorsa AddressView sul database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Aggiornamento di AddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Aggiornamento corretto"),
			@ApiResponse(code = 500, message = "Aggiornamento non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PutMapping("update")
	public ResponseEntity<?> update(AddressView instance) {
		if (instance.getID() == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene aggiornata la risorsa l'instanca sul DB
		Boolean isUpdated = getService().update(instance);

		MyResponseStatus response = null;
		if (isUpdated == false)
			response = MyResponseStatus.NO_CONTENT;
		else
			response = MyResponseStatus.OK;
		return getResponseWithStatus(response, "Aggiornamento AddressView terminato");
	}

	/**
	 * gateway/personservice/address/delete Cancellazione di una risorsa AddressView sul database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Cancellazione di AddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cancellazione effettuata con successo"),
			@ApiResponse(code = 500, message = "Cancellazione effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(AddressView instance) {
		if (instance.getID() == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		Boolean isDeleted = getService().delete(instance);

		MyResponseStatus response = null;
		if (isDeleted == true)
			response = MyResponseStatus.OK;
		else
			response = MyResponseStatus.BAD;
		return getResponseWithStatus(response, "Cancellazione AddressView terminato");

	}

	/**
	 * gateway/personservice/address/delete/{ID} Cancellazione di una risorsa AddressView sul database.
	 * 
	 * @param ID la primary key dell'istanza da cancellare
	 */
	@ApiOperation(value = "Cancellazione di AddressView dato il suo ID", response = ResponseEntity.class, produces = "application/json")
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
		return getResponseWithStatus(response, "Cancellazione AddressView con ID=" + ID + " terminato");
	}

	/**
	 * gateway/personservice/address/get/{ID} Richiesta di una risorsa AddressView dal database.
	 * 
	 * @param ID la primary key dell'istanza che si vuole ottenere
	 */
	@ApiOperation(value = "Restituzione risorsa Address", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("get/{ID}")
	public ResponseEntity<?> get(@PathVariable("ID") Long ID) {
		if (ID == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		return getResponse(getService().findByID(ID), "Richiesta di AddressView terminata");
	}

	/**
	 * gateway/personservice/address/getAll Richiesta di tutte le risorse AddressView dal database.
	 */
	@ApiOperation(value = "Restituzione i tutte le risorse Address", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return getResponse(getService().findAll(), "Richiesta di AddressView terminata");
	}

	/**
	 * <p>
	 * gateway/invoiceservice/address/getAddressByID <br>
	 * Restituzione di una risorsa in formato Json di Address
	 * </p>
	 */
	@ApiOperation(value = "Restituzione di una risorsa in formato Json di Address", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping(value = "getAddressByID/{ID}")
	public String getAddressByID(@PathVariable("ID") Long ID) {
		if (ID == null)
			return null;
		return getService().findByIdToJson(ID);
	}

	/*****************************************************************/
	/*************************** FRONT_END *****************************/
	/*****************************************************************/

	/**
	 * gateway/personservice/address/view/{ID} Richiesta di una risorsa AddressView dal database.
	 * 
	 * @param ID la primary key dell'istanza che si vuole visualizzare
	 */
	@ApiOperation(value = "Vista frontend di Address", response = ResponseEntity.class, produces = "application/json")
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
	 * gateway/personservice/address/input Richiesta della pagina di inserimento dati di AddressView
	 */
	@ApiOperation(value = "Input da frontend di Address", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Richiesta effettuata con successo"),
			@ApiResponse(code = 500, message = "Richiesta effettuata senza successo"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@GetMapping("input")
	public ModelAndView input() {
		return getService().input();
	}

	/**
	 * gateway/personservice/address/search Richiesta della pagina di ricerca dati di AddressView
	 */
	@ApiOperation(value = "Ricerca frontend di Address", response = ResponseEntity.class, produces = "application/json")
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
	 * gateway/personservice/address/storeView Memorizzazione di una risorsa AddressView sul database.
	 * 
	 * @param instance l'oggetto passato dal frontend
	 */
	@ApiOperation(value = "Salvataggio da frontend di AddressView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("storeView")
	public ModelAndView storeView(AddressView instance) {
		// if(instance.getID()==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);
		if (ID == null)
			return null;
		return getService().view(ID);
	}

}
