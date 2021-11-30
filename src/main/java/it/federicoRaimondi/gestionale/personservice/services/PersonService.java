package it.federicoRaimondi.gestionale.personservice.services;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.federicoRaimondi.gestionale.personservice.daoServices.PersonDAO;
import it.federicoRaimondi.gestionale.personservice.views.PersonView;

@Service
@Transactional
public class PersonService {

	private PersonDAO repository;

	public PersonService(PersonDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa PersonView
	 * 
	 * @param  instance l'oggetto PersonView da salvate nel DB
	 * @return          l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(PersonView instance) {
		if (instance == null)
			return null;
		Long id = repository.store(instance);
		return id;
	}

	/**
	 * Aggiornamento di una risorsa PersonView
	 * 
	 * @param  instance l'oggetto PersonView da rimpiazzare
	 * @return          true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(PersonView instance) {
		if (instance.getID() == null)
			return false;
		Boolean result = repository.update(instance);
		return result;
	}

	/**
	 * Cancellazione di una risorsa PersonView
	 * 
	 * @param  instance l'oggetto PersonView da rimpiazzare
	 * @return          true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(PersonView instance) {
		Boolean result = repository.deleteInstance(instance);
		return result;
	}

	/**
	 * Cancellazione di una risorsa PersonView dato il suo ID
	 * 
	 * @param  ID l'identificativo della risorsa
	 * @return    true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {
		if (id == null)
			return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}

	public PersonView findByID(Long id) {
		PersonView instance = repository.findByID(id);
		return instance;
	}

	public List<PersonView> findAll() {
		return repository.findAll();
	}

	/*****************************************************************/
	/*************************** FRONT_END *****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		PersonView view = findByID(ID);
		if (view == null)
			return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("PersonView");
		model.addObject("title", "Person_" + String.valueOf(ID));

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
		model.setViewName("PersonInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<PersonView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("PersonSearch");
		return model;
	}

}
