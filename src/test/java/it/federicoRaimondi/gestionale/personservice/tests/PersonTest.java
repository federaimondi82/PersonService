package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.PersonBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.PersonDAO;
import it.federicoRaimondi.gestionale.personservice.services.PersonService;
import it.federicoRaimondi.gestionale.personservice.views.PersonView;

@SpringBootTest
class PersonTests {
	
	@Autowired
	public PersonDAO repo;

	@Test
	public final void createPersonView() {
		//crea un oggetto
		PersonBuilder builder = new PersonBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
		PersonView view = builder.build();
		PersonService service = new PersonService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readPersonView() {
		//crea un oggetto
		PersonBuilder builder = new PersonBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
		PersonView view = builder.build();
		
		PersonService service = new PersonService(repo);
		Long id =service.store(view);
		PersonView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updatePersonView() {
		//crea un oggetto
		PersonBuilder builder = new PersonBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
		PersonView view = builder.build();
		
		PersonService service = new PersonService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		PersonView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setName(null);	
        	view2.setSurname(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deletePersonView() {
		//crea un oggetto
		
		PersonBuilder builder = new PersonBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
		PersonView view = builder.build();
		
		PersonService service = new PersonService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		PersonView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
