package it.federicoRaimondi.gestionale.personservice.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.StateBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.StateDAO;
import it.federicoRaimondi.gestionale.personservice.services.StateService;
import it.federicoRaimondi.gestionale.personservice.views.StateView;

@SpringBootTest
class StateTests {

	@Autowired
	public StateDAO repo;

	@Test
	public final void createStateView() {
		// crea un oggetto
		StateBuilder builder = new StateBuilder();
		builder.setName("Italia");
		StateView view = builder.build();
		StateService service = new StateService(repo);
		Long id = service.store(view);
		assertTrue(id != null);
	}

	@Test
	void readStateView() {
		// crea un oggetto
		StateBuilder builder = new StateBuilder();
		builder.setName("Italia");
		StateView view = builder.build();

		StateService service = new StateService(repo);
		Long id = service.store(view);
		StateView view2 = service.findByID(id);
		assertTrue(view2 != null);
	}

	@Test
	void updateStateView() {
		// crea un oggetto
		StateBuilder builder = new StateBuilder();
		builder.setID(null);
		builder.setName("Italia");
		StateView view = builder.build();

		StateService service = new StateService(repo);
		Long id = service.store(view);
		assertTrue(id != null);

		// richiamo l'oggetto
		StateView view2 = service.findByID(id);
		// cambio qualcosa
		view2.setName("Germania");

		Long id2 = service.store(view2);
		assertTrue(id.equals(id2));
	}

	@Test
	void deleteStateView() {
		// crea un oggetto

		StateBuilder builder = new StateBuilder();
		builder.setID(null);
		builder.setName("Italia");
		StateView view = builder.build();

		StateService service = new StateService(repo);
		Long id = service.store(view);
		assertTrue(id != null);
		service.deleteByID(id);
		StateView view2 = service.findByID(id);
		assertTrue(view2 == null);
	}

}
