package it.federicoRaimondi.gestionale.personservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.federicoRaimondi.gestionale.personservice.views.PersonView;
import it.federicoRaimondi.gestionale.personservice.baseControllers.BaseController;
import it.federicoRaimondi.gestionale.personservice.baseControllers.MyResponseStatus;
import it.federicoRaimondi.gestionale.personservice.services.PersonService;
import it.federicoRaimondi.gestionale.personservice.daoServices.PersonDAO;
import it.federicoRaimondi.gestionale.personservice.adapters.PersonAdapter;

@RestController
@RequestMapping("personservice/person")
@Api(value="PersonService", tags="Controller per le chiamate su PersonService")
public class PersonController extends BaseController{

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PersonController.class.getName());
	private PersonService service;
	
	@Autowired
	private PersonDAO repository;
	
	public PersonService getService(){
		 service = new PersonService(repository);
		 return service;
	}

	/**
     * gateway/personservice/person/store
     * Memorizzazione di una risorsa PersonView sul database.
     * @param instance l'oggetto passato dal fontend
     */
    @ApiOperation(value="Salvataggio di PersonView", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Salvato correttamente"),
		@ApiResponse(code=500, message="Il salvataggio non corretto"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@PostMapping("store")
    public ResponseEntity<?> store(PersonView instance) {    	
		//Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);
		
		return getResponse(ID, "Salvataggio InvoiceView terminato");
    }
    
    /**
	 * gateway/personservice/person/storeMap 
	 * Memorizzazione di una risorsa PersonView sul database.
	 * 
	 * @param instance l'oggetto passato dal fontend
	 */
	@ApiOperation(value = "Salvataggio di PersonView", response = ResponseEntity.class, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salvato correttamente"),
			@ApiResponse(code = 500, message = "Il salvataggio non corretto"),
			@ApiResponse(code = 401, message = "Non sei autorizzato"),
			@ApiResponse(code = 403, message = "Non sei autenticato") })
	@PostMapping("storeMap")
	public ResponseEntity<?> storeMap(Map<String, Object> instance) {
		if (instance == null)
			getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");

		// Viene salvata l'instanca sul DB
		Long ID = getService().store(PersonAdapter.getInstance().toObject(instance));
		
		return getResponse(ID, "Salvataggio PersonView terminato");
	}
    
    /**
     * gateway/personservice/person/update
     * Aggiorna una risorsa PersonView sul database.
     * @param instance l'oggetto passato dal fontend
     */
    @ApiOperation(value="Aggiornamento di PersonView", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Aggiornamento corretto"),
		@ApiResponse(code=500, message="Aggiornamento non corretto"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@PutMapping("update")
    public ResponseEntity<?> update(PersonView instance) {
    	if(instance.getID()==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		
		//Viene aggiornata la risorsa l'instanca sul DB
		Boolean isUpdated = getService().update(instance);
		
		MyResponseStatus response = null;
		if(isUpdated==false) response = MyResponseStatus.NO_CONTENT;
		else response = MyResponseStatus.OK;
		return getResponseWithStatus(response, "Aggiornamento PersonView terminato");
    }
    
    /**
     * gateway/personservice/person/delete
     * Cancellazione di una risorsa PersonView sul database.
     * @param instance l'oggetto passato dal fontend
     */
    @ApiOperation(value="Cancellazione di PersonView", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Cancellazione effettuata con successo"),
		@ApiResponse(code=500, message="Cancellazione effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@DeleteMapping("delete")
    public ResponseEntity<?> delete(PersonView instance) {
		if(instance.getID()==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		
		Boolean isDeleted = getService().delete(instance);
		
		MyResponseStatus response = null;
		if(isDeleted==true) response = MyResponseStatus.OK;
		else response = MyResponseStatus.BAD;
		return getResponseWithStatus(response,  "Cancellazione PersonView terminato");
		
    }
    
     /**
     * gateway/personservice/person/delete/{ID}
     * Cancellazione di una risorsa PersonView sul database.
     * @param ID la primary key dell'istanza da cancellare
     */
    @ApiOperation(value="Cancellazione di PersonView dato il suo ID", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Cancellazione effettuata con successo"),
		@ApiResponse(code=500, message="Cancellazione effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@DeleteMapping("delete/{ID}")
    public ResponseEntity<?> deleteByID(@PathVariable("ID") Long ID) {
		if(ID==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		Boolean isDeleted = getService().deleteByID(ID);
		
		MyResponseStatus response = null;
		if(isDeleted==true) response = MyResponseStatus.OK;
		else response = MyResponseStatus.BAD;
		return getResponseWithStatus(response,  "Cancellazione PersonView con ID="+ID+" terminato");
    }
    
     /**
     * gateway/personservice/person/get/{ID}
     * Richiesta di una risorsa PersonView dal database.
     * @param ID la primary key dell'istanza che si vuole ottenere
     */
    @ApiOperation(value="Restituzione risorsa Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@GetMapping("get/{ID}")
    public ResponseEntity<?> get(@PathVariable("ID") Long ID) {
    	if(ID==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		return getResponse(getService().findByID(ID), "Richiesta di PersonView terminata");
    }
    
     /**
     * gateway/personservice/person/getAll
     * Richiesta di tutte le risorse PersonView dal database.
     */
    @ApiOperation(value="Restituzione i tutte le risorse Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@GetMapping("getAll")
    public ResponseEntity<?> getAll() {
		return getResponse(getService().findAll(), "Richiesta di PersonView terminata");
    }
    
    /**
     * gateway/personservice/person/getPersonByID
     * Restituzione di una risorsa in formato Map di Person
     */
    @ApiOperation(value="Restituzione di una risorsa in formato Map di Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
    @GetMapping(value = "/articolo/getPersonByID/{ID}")
	public Map<String, Object> getPersonByID(@PathVariable("ID") Long ID) {
		if (ID == null)
			return null;
		return PersonAdapter.getInstance().adapt(getService().findByID(ID)).toJson();
	}
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/
    
    /**
     * gateway/personservice/person/view/{ID}
     * Richiesta di una risorsa PersonView dal database.
     * @param ID la primary key dell'istanza che si vuole visualizzare 
     */
    @ApiOperation(value="Vista frontend di Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
  	@GetMapping("view/{ID}")
	public ModelAndView view(@PathVariable("ID") Long ID) {
		if (ID == null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
		return getService().view(ID);
	}
	
    /**
     * gateway/personservice/person/input
     * Richiesta della pagina di inserimento dati di PersonView
     */
    @ApiOperation(value="Input da frontend di Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
  	@GetMapping("input")
	public ModelAndView input() {
		return getService().input();
	}
	
	
	/**
     * gateway/personservice/person/search
     * Richiesta della pagina di ricerca dati di PersonView
     */
    @ApiOperation(value="Ricerca frontend di Person", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Richiesta effettuata con successo"),
		@ApiResponse(code=500, message="Richiesta effettuata senza successo"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
  	@GetMapping("search")
	public ModelAndView search() {
		//TODO
		//return getService().search();
		return null;
	}
	
	/**
     * gateway/personservice/person/storeView
     * Memorizzazione di una risorsa PersonView sul database.
     * @param instance l'oggetto passato dal frontend
     */
    @ApiOperation(value="Salvataggio da frontend di PersonView", response=ResponseEntity.class, produces="application/json")
	@ApiResponses({@ApiResponse(code=200, message="Salvato correttamente"),
		@ApiResponse(code=500, message="Il salvataggio non corretto"),
		@ApiResponse(code=401, message="Non sei autorizzato"),
		@ApiResponse(code=403, message="Non sei autenticato")})
	@PostMapping("storeView")
    public ModelAndView storeView(PersonView instance) {
    	//if(instance.getID()==null) getResponseWithStatus(MyResponseStatus.BAD, "ID non valido");
    	
		//Viene salvata l'instanca sul DB
		Long ID = getService().store(instance);
		if(ID==null) return null;
		return getService().view(ID);
    }
   

}
