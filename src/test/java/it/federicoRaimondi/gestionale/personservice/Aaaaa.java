package it.federicoRaimondi.gestionale.personservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.federicoRaimondi.gestionale.personservice.builders.AddressBuilder;
import it.federicoRaimondi.gestionale.personservice.builders.CountryBuilder;
import it.federicoRaimondi.gestionale.personservice.builders.PersonBuilder;
import it.federicoRaimondi.gestionale.personservice.builders.StateBuilder;
import it.federicoRaimondi.gestionale.personservice.daoServices.AddressDAO;
import it.federicoRaimondi.gestionale.personservice.daoServices.CountryDAO;
import it.federicoRaimondi.gestionale.personservice.daoServices.PersonDAO;
import it.federicoRaimondi.gestionale.personservice.daoServices.StateDAO;
import it.federicoRaimondi.gestionale.personservice.services.AddressService;
import it.federicoRaimondi.gestionale.personservice.services.CountryService;
import it.federicoRaimondi.gestionale.personservice.services.PersonService;
import it.federicoRaimondi.gestionale.personservice.services.StateService;
import it.federicoRaimondi.gestionale.personservice.views.AddressView;
import it.federicoRaimondi.gestionale.personservice.views.CountryView;
import it.federicoRaimondi.gestionale.personservice.views.PersonView;
import it.federicoRaimondi.gestionale.personservice.views.StateView;

@SpringBootTest
class Aaaaa {

	@Autowired
	public PersonDAO personRepo;
	@Autowired
	public AddressDAO addressRepo;
	@Autowired
	public StateDAO stateRepo;
	@Autowired
	public CountryDAO countryRepo;

	@Test
	public final void start_1() {
		Long personID = createPersonView();
		Long stateID = createStateView();
		Long countryID = createCountryView(stateID, personID);
		Long addressID = createAddressView(personID);

	}

	public Long createAddressView(Long personID) {
		// crea un oggetto
		AddressBuilder builder = new AddressBuilder();
		builder.setAddress("Via Trevi");
		builder.setNumber(3);
		builder.setPersonID(personID);
		AddressView view = builder.build();
		AddressService service = new AddressService(addressRepo);
		Long id = service.store(view);
		assertTrue(id != null);
		return id;
	}

	public Long createPersonView() {
		// crea un oggetto
		PersonBuilder builder = new PersonBuilder();
		builder.setName("Mario");
		builder.setSurname("Bianchi");
		PersonView view = builder.build();
		PersonService service = new PersonService(personRepo);
		Long id = service.store(view);
		assertTrue(id != null);
		return id;
	}

	public Long createStateView() {
		// crea un oggetto
		StateBuilder builder = new StateBuilder();
		builder.setName("Italia");
		StateView view = builder.build();
		StateService service = new StateService(stateRepo);
		Long id = service.store(view);
		assertTrue(id != null);
		return id;
	}

	public Long createCountryView(Long stateID, Long personID) {
		// crea un oggetto
		CountryBuilder builder = new CountryBuilder();
		builder.setName("Roma");
		builder.setPersonID(personID);
		builder.setStateID(stateID);
		CountryView view = builder.build();
		CountryService service = new CountryService(countryRepo);
		Long id = service.store(view);
		assertTrue(id != null);
		return id;
	}

}
