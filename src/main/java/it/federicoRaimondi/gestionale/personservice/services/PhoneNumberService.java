package it.federicoRaimondi.gestionale.personservice.services;

import it.federicoRaimondi.gestionale.personservice.views.PhoneNumberView;
import it.federicoRaimondi.gestionale.personservice.daoServices.PhoneNumberDAO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PhoneNumberService {

	private PhoneNumberDAO repository;

	public PhoneNumberService(PhoneNumberDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa PhoneNumberView
	 * @param instance l'oggetto PhoneNumberView da salvate nel DB
	 * @return l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(PhoneNumberView instance) {	
		if(instance==null) return null;	
		Long id = repository.store(instance);
		return id;
	}
	
	/**
	 * Aggiornamento di una risorsa PhoneNumberView
	 * @param instance l'oggetto PhoneNumberView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(PhoneNumberView instance) {
		if(instance.getID()==null) return false;
		Boolean result = repository.update(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa PhoneNumberView
	 * @param instance l'oggetto PhoneNumberView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(PhoneNumberView instance) {		
		Boolean result = repository.deleteInstance(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa PhoneNumberView dato il suo ID
	 * @param ID l'identificativo della risorsa
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {		
		if(id==null) return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}
	
	public PhoneNumberView findByID(Long id) {
		PhoneNumberView instance = repository.findByID(id);
		return instance;
	}
	
	public List<PhoneNumberView> findAll() {
		return repository.findAll();
	}
	
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		PhoneNumberView view = findByID(ID);
		if (view == null)	return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("PhoneNumberView");
		model.addObject("title", "PhoneNumber_" + String.valueOf(ID));

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
		model.setViewName("PhoneNumberInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<PhoneNumberView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("PhoneNumberSearch");
		return model;
	}

}
