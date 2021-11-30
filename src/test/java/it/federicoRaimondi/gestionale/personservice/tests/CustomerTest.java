package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.CustomerBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.CustomerDAO;
import it.federicoRaimondi.gestionale.personservice.services.CustomerService;
import it.federicoRaimondi.gestionale.personservice.views.CustomerView;

@SpringBootTest
class CustomerTests {
	
	@Autowired
	public CustomerDAO repo;

	@Test
	public final void createCustomerView() {
		//crea un oggetto
		CustomerBuilder builder = new CustomerBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagioneSociale(null);	
		CustomerView view = builder.build();
		CustomerService service = new CustomerService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readCustomerView() {
		//crea un oggetto
		CustomerBuilder builder = new CustomerBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagioneSociale(null);	
		CustomerView view = builder.build();
		
		CustomerService service = new CustomerService(repo);
		Long id =service.store(view);
		CustomerView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updateCustomerView() {
		//crea un oggetto
		CustomerBuilder builder = new CustomerBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagioneSociale(null);	
		CustomerView view = builder.build();
		
		CustomerService service = new CustomerService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		CustomerView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setName(null);	
        	view2.setSurname(null);	
        	view2.setPartitaIva(null);	
        	view2.setRagioneSociale(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deleteCustomerView() {
		//crea un oggetto
		
		CustomerBuilder builder = new CustomerBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagioneSociale(null);	
		CustomerView view = builder.build();
		
		CustomerService service = new CustomerService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		CustomerView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
