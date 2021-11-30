package it.federicoRaimondi.gestionale.personservice.services;

import it.federicoRaimondi.gestionale.personservice.views.SupplierView;
import it.federicoRaimondi.gestionale.personservice.daoServices.SupplierDAO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class SupplierService {

	private SupplierDAO repository;

	public SupplierService(SupplierDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa SupplierView
	 * @param instance l'oggetto SupplierView da salvate nel DB
	 * @return l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(SupplierView instance) {	
		if(instance==null) return null;	
		Long id = repository.store(instance);
		return id;
	}
	
	/**
	 * Aggiornamento di una risorsa SupplierView
	 * @param instance l'oggetto SupplierView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(SupplierView instance) {
		if(instance.getID()==null) return false;
		Boolean result = repository.update(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa SupplierView
	 * @param instance l'oggetto SupplierView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(SupplierView instance) {		
		Boolean result = repository.deleteInstance(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa SupplierView dato il suo ID
	 * @param ID l'identificativo della risorsa
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {		
		if(id==null) return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}
	
	public SupplierView findByID(Long id) {
		SupplierView instance = repository.findByID(id);
		return instance;
	}
	
	public List<SupplierView> findAll() {
		return repository.findAll();
	}
	
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		SupplierView view = findByID(ID);
		if (view == null)	return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("SupplierView");
		model.addObject("title", "Supplier_" + String.valueOf(ID));

		List<Field> listFiled = Arrays.asList(view.getClass().getDeclaredFields());
		for (Field el : listFiled) {
			el.setAccessible(true);
			try {
				model.addObject(el.getName(), el.get(view));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}
		return model;
	}
	
	public ModelAndView input() {
		ModelAndView model = new ModelAndView();
		model.setViewName("SupplierInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<SupplierView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("SupplierSearch");
		return model;
	}

}
