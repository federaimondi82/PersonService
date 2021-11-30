package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.TransactionalException;

import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;

@Repository
public interface PhoneNumberDAO extends CrudRepository<PhoneNumberView, Long>{

	static final Logger logger = Logger.getLogger(PhoneNumberDAO.class.getName());
	
	default PhoneNumberView findByID(Long id) {
		try {
			PhoneNumberView instance = findById(id).get();
			return instance;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	default List<PhoneNumberView> findAll() {
		try {
			List<PhoneNumberView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(PhoneNumberView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(PhoneNumberView instance) {
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
			PhoneNumberView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(PhoneNumberView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
