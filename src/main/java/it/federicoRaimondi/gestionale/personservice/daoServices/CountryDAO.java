package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.TransactionalException;

import it.federicoRaimondi.gestionale.personservice.views.CountryView;

@Repository
public interface CountryDAO extends CrudRepository<CountryView, Long>{

	static final Logger logger = Logger.getLogger(CountryDAO.class.getName());
	
	default CountryView findByID(Long id) {
		try {
			CountryView instance = findById(id).get();
			return instance;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	default List<CountryView> findAll() {
		try {
			List<CountryView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(CountryView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(CountryView instance) {
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
			CountryView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(CountryView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
