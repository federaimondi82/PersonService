package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.PhoneNumberBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.PhoneNumberDAO;
import it.federicoRaimondi.gestionale.personservice.services.PhoneNumberService;
import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;

@SpringBootTest
class PhoneNumberTests {
	
	@Autowired
	public PhoneNumberDAO repo;

	@Test
	public final void createPhoneNumberView() {
		//crea un oggetto
		PhoneNumberBuilder builder = new PhoneNumberBuilder();
        	builder.setID(null);	
        	builder.setNumber(null);	
        	builder.setType(null);	
        	builder.setPersonID(null);	
		PhoneNumberView view = builder.build();
		PhoneNumberService service = new PhoneNumberService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readPhoneNumberView() {
		//crea un oggetto
		PhoneNumberBuilder builder = new PhoneNumberBuilder();
        	builder.setID(null);	
        	builder.setNumber(null);	
        	builder.setType(null);	
        	builder.setPersonID(null);	
		PhoneNumberView view = builder.build();
		
		PhoneNumberService service = new PhoneNumberService(repo);
		Long id =service.store(view);
		PhoneNumberView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updatePhoneNumberView() {
		//crea un oggetto
		PhoneNumberBuilder builder = new PhoneNumberBuilder();
        	builder.setID(null);	
        	builder.setNumber(null);	
        	builder.setType(null);	
        	builder.setPersonID(null);	
		PhoneNumberView view = builder.build();
		
		PhoneNumberService service = new PhoneNumberService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		PhoneNumberView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setNumber(null);	
        	view2.setType(null);	
        	view2.setPersonID(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deletePhoneNumberView() {
		//crea un oggetto
		
		PhoneNumberBuilder builder = new PhoneNumberBuilder();
        	builder.setID(null);	
        	builder.setNumber(null);	
        	builder.setType(null);	
        	builder.setPersonID(null);	
		PhoneNumberView view = builder.build();
		
		PhoneNumberService service = new PhoneNumberService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		PhoneNumberView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
