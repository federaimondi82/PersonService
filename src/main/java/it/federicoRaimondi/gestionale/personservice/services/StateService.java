package it.federicoRaimondi.gestionale.personservice.services;

import it.federicoRaimondi.gestionale.personservice.views.StateView;
import it.federicoRaimondi.gestionale.personservice.daoServices.StateDAO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class StateService {

	private StateDAO repository;

	public StateService(StateDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa StateView
	 * @param instance l'oggetto StateView da salvate nel DB
	 * @return l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(StateView instance) {	
		if(instance==null) return null;	
		Long id = repository.store(instance);
		return id;
	}
	
	/**
	 * Aggiornamento di una risorsa StateView
	 * @param instance l'oggetto StateView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(StateView instance) {
		if(instance.getID()==null) return false;
		Boolean result = repository.update(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa StateView
	 * @param instance l'oggetto StateView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(StateView instance) {		
		Boolean result = repository.deleteInstance(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa StateView dato il suo ID
	 * @param ID l'identificativo della risorsa
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {		
		if(id==null) return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}
	
	public StateView findByID(Long id) {
		StateView instance = repository.findByID(id);
		return instance;
	}
	
	public List<StateView> findAll() {
		return repository.findAll();
	}
	
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		StateView view = findByID(ID);
		if (view == null)	return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("StateView");
		model.addObject("title", "State_" + String.valueOf(ID));

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
		model.setViewName("StateInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<StateView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("StateSearch");
		return model;
	}

}
