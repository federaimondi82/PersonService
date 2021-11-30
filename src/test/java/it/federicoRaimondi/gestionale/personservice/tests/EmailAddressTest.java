package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.EmailAddressBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.EmailAddressDAO;
import it.federicoRaimondi.gestionale.personservice.services.EmailAddressService;
import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;

@SpringBootTest
class EmailAddressTests {
	
	@Autowired
	public EmailAddressDAO repo;

	@Test
	public final void createEmailAddressView() {
		//crea un oggetto
		EmailAddressBuilder builder = new EmailAddressBuilder();
        	builder.setID(null);	
        	builder.setEmail(null);	
        	builder.setPersonID(null);	
		EmailAddressView view = builder.build();
		EmailAddressService service = new EmailAddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readEmailAddressView() {
		//crea un oggetto
		EmailAddressBuilder builder = new EmailAddressBuilder();
        	builder.setID(null);	
        	builder.setEmail(null);	
        	builder.setPersonID(null);	
		EmailAddressView view = builder.build();
		
		EmailAddressService service = new EmailAddressService(repo);
		Long id =service.store(view);
		EmailAddressView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updateEmailAddressView() {
		//crea un oggetto
		EmailAddressBuilder builder = new EmailAddressBuilder();
        	builder.setID(null);	
        	builder.setEmail(null);	
        	builder.setPersonID(null);	
		EmailAddressView view = builder.build();
		
		EmailAddressService service = new EmailAddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		EmailAddressView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setEmail(null);	
        	view2.setPersonID(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deleteEmailAddressView() {
		//crea un oggetto
		
		EmailAddressBuilder builder = new EmailAddressBuilder();
        	builder.setID(null);	
        	builder.setEmail(null);	
        	builder.setPersonID(null);	
		EmailAddressView view = builder.build();
		
		EmailAddressService service = new EmailAddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		EmailAddressView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
