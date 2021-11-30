package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.TransactionalException;

import it.federicoRaimondi.gestionale.personservice.views.CustomerView;

@Repository
public interface CustomerDAO extends CrudRepository<CustomerView, Long>{

	static final Logger logger = Logger.getLogger(CustomerDAO.class.getName());
	
	default CustomerView findByID(Long id) {
		try {
			CustomerView instance = findById(id).get();
			return instance;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	default List<CustomerView> findAll() {
		try {
			List<CustomerView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(CustomerView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(CustomerView instance) {
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
			CustomerView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(CustomerView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
