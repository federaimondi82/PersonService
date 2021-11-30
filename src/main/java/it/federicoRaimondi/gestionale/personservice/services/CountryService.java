package it.federicoRaimondi.gestionale.personservice.services;

import it.federicoRaimondi.gestionale.personservice.views.CountryView;
import it.federicoRaimondi.gestionale.personservice.daoServices.CountryDAO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CountryService {

	private CountryDAO repository;

	public CountryService(CountryDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa CountryView
	 * @param instance l'oggetto CountryView da salvate nel DB
	 * @return l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(CountryView instance) {	
		if(instance==null) return null;	
		Long id = repository.store(instance);
		return id;
	}
	
	/**
	 * Aggiornamento di una risorsa CountryView
	 * @param instance l'oggetto CountryView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(CountryView instance) {
		if(instance.getID()==null) return false;
		Boolean result = repository.update(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa CountryView
	 * @param instance l'oggetto CountryView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(CountryView instance) {		
		Boolean result = repository.deleteInstance(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa CountryView dato il suo ID
	 * @param ID l'identificativo della risorsa
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {		
		if(id==null) return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}
	
	public CountryView findByID(Long id) {
		CountryView instance = repository.findByID(id);
		return instance;
	}
	
	public List<CountryView> findAll() {
		return repository.findAll();
	}
	
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		CountryView view = findByID(ID);
		if (view == null)	return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("CountryView");
		model.addObject("title", "Country_" + String.valueOf(ID));

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
		model.setViewName("CountryInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<CountryView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("CountrySearch");
		return model;
	}

}
