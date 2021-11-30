package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.transaction.TransactionalException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.federicoRaimondi.gestionale.personservice.views.PersonView;

@Repository
public interface PersonDAO extends CrudRepository<PersonView, Long> {

	static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

	default PersonView findByID(Long id) {
		try {
			PersonView instance = findById(id).get();
			return instance;
		} catch (NoSuchElementException | NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default List<PersonView> findAll() {
		try {
			List<PersonView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(PersonView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(PersonView instance) {
		try {
			save(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteByID(Long id) {
		try {
			PersonView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(PersonView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
