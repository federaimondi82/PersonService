package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.CountryBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.CountryDAO;
import it.federicoRaimondi.gestionale.personservice.services.CountryService;
import it.federicoRaimondi.gestionale.personservice.views.CountryView;

@SpringBootTest
class CountryTests {
	
	@Autowired
	public CountryDAO repo;

	@Test
	public final void createCountryView() {
		//crea un oggetto
		CountryBuilder builder = new CountryBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setPersonID(null);	
        	builder.setStateID(null);	
		CountryView view = builder.build();
		CountryService service = new CountryService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readCountryView() {
		//crea un oggetto
		CountryBuilder builder = new CountryBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setPersonID(null);	
        	builder.setStateID(null);	
		CountryView view = builder.build();
		
		CountryService service = new CountryService(repo);
		Long id =service.store(view);
		CountryView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updateCountryView() {
		//crea un oggetto
		CountryBuilder builder = new CountryBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setPersonID(null);	
        	builder.setStateID(null);	
		CountryView view = builder.build();
		
		CountryService service = new CountryService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		CountryView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setName(null);	
        	view2.setPersonID(null);	
        	view2.setStateID(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deleteCountryView() {
		//crea un oggetto
		
		CountryBuilder builder = new CountryBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setPersonID(null);	
        	builder.setStateID(null);	
		CountryView view = builder.build();
		
		CountryService service = new CountryService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		CountryView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
