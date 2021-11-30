package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.SupplierBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.SupplierDAO;
import it.federicoRaimondi.gestionale.personservice.services.SupplierService;
import it.federicoRaimondi.gestionale.personservice.views.SupplierView;

@SpringBootTest
class SupplierTests {
	
	@Autowired
	public SupplierDAO repo;

	@Test
	public final void createSupplierView() {
		//crea un oggetto
		SupplierBuilder builder = new SupplierBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagionaSociale(null);	
		SupplierView view = builder.build();
		SupplierService service = new SupplierService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);		
	}
	
	@Test
	void readSupplierView() {
		//crea un oggetto
		SupplierBuilder builder = new SupplierBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagionaSociale(null);	
		SupplierView view = builder.build();
		
		SupplierService service = new SupplierService(repo);
		Long id =service.store(view);
		SupplierView view2 = service.findByID(id);
		assertTrue(view2!=null);	
	}
	
	@Test
	void updateSupplierView() {
		//crea un oggetto
		SupplierBuilder builder = new SupplierBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagionaSociale(null);	
		SupplierView view = builder.build();
		
		SupplierService service = new SupplierService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
			
		//richiamo l'oggetto
		SupplierView view2 = service.findByID(id);
		//cambio qualcosa
        	view2.setID(null);	
        	view2.setName(null);	
        	view2.setSurname(null);	
        	view2.setPartitaIva(null);	
        	view2.setRagionaSociale(null);	
		
		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}
	
	@Test
	void deleteSupplierView() {
		//crea un oggetto
		
		SupplierBuilder builder = new SupplierBuilder();
        	builder.setID(null);	
        	builder.setName(null);	
        	builder.setSurname(null);	
        	builder.setPartitaIva(null);	
        	builder.setRagionaSociale(null);	
		SupplierView view = builder.build();
		
		SupplierService service = new SupplierService(repo);
		Long id =service.store(view);
		assertTrue(id!=null);	
		service.deleteByID(id);
		SupplierView view2 = service.findByID(id);
		assertTrue(view2==null);
	}

}
