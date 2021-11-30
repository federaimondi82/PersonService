package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.TransactionalException;

import it.federicoRaimondi.gestionale.personservice.views.StateView;

@Repository
public interface StateDAO extends CrudRepository<StateView, Long>{

	static final Logger logger = Logger.getLogger(StateDAO.class.getName());
	
	default StateView findByID(Long id) {
		try {
			StateView instance = findById(id).get();
			return instance;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	default List<StateView> findAll() {
		try {
			List<StateView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(StateView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(StateView instance) {
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
			StateView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(StateView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
