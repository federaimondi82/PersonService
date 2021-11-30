package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.AddressBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.AddressDAO;
import it.federicoRaimondi.gestionale.personservice.services.AddressService;
import it.federicoRaimondi.gestionale.personservice.views.AddressView;

@SpringBootTest
class AddressTests {
	
	@Autowired
	public AddressDAO repo;

	@Test
	public final void createAddressView() {
		//crea un oggetto
		AddressBuilder builder = new AddressBuilder();
        	builder.setID(null);	
        	builder.setAddress(null);	
        	builder.setNumber(null);	
        	builder.setPersonID(null);	
		AddressView view = builder.build();
		AddressService service = new AddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readAddressView() {
		//crea un oggetto
		AddressBuilder builder = new AddressBuilder();
        	builder.setID(null);	
        	builder.setAddress(null);	
        	builder.setNumber(null);	
        	builder.setPersonID(null);	
		AddressView view = builder.build();
		
		AddressService service = new AddressService(repo);
		Long id =service.store(view);
		AddressView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updateAddressView() {
		//crea un oggetto
		AddressBuilder builder = new AddressBuilder();
        	builder.setID(null);	
        	builder.setAddress(null);	
        	builder.setNumber(null);	
        	builder.setPersonID(null);	
		AddressView view = builder.build();
		
		AddressService service = new AddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		AddressView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setAddress(null);	
        	view2.setNumber(null);	
        	view2.setPersonID(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deleteAddressView() {
		//crea un oggetto
		
		AddressBuilder builder = new AddressBuilder();
        	builder.setID(null);	
        	builder.setAddress(null);	
        	builder.setNumber(null);	
        	builder.setPersonID(null);	
		AddressView view = builder.build();
		
		AddressService service = new AddressService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		AddressView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
