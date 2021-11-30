package it.federicoRaimondi.gestionale.personservice.daoServices;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.TransactionalException;

import it.federicoRaimondi.gestionale.personservice.views.SupplierView;

@Repository
public interface SupplierDAO extends CrudRepository<SupplierView, Long>{

	static final Logger logger = Logger.getLogger(SupplierDAO.class.getName());
	
	default SupplierView findByID(Long id) {
		try {
			SupplierView instance = findById(id).get();
			return instance;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	default List<SupplierView> findAll() {
		try {
			List<SupplierView> list = findAll();
			return list;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Long store(SupplierView instance) {
		try {
			save(instance);
			return instance.getID();
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	default Boolean update(SupplierView instance) {
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
			SupplierView instance = findById(id).get();
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	default Boolean deleteInstance(SupplierView instance) {
		try {
			delete(instance);
			return true;
		} catch (NullPointerException | TransactionalException | IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
