package it.federicoRaimondi.gestionale.personservice.services;

import it.federicoRaimondi.gestionale.personservice.views.EmailAddressView;
import it.federicoRaimondi.gestionale.personservice.daoServices.EmailAddressDAO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class EmailAddressService {

	private EmailAddressDAO repository;

	public EmailAddressService(EmailAddressDAO repository) {
		this.repository = repository;
	}

	/**
	 * Salvataggio di una risorsa EmailAddressView
	 * @param instance l'oggetto EmailAddressView da salvate nel DB
	 * @return l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(EmailAddressView instance) {	
		if(instance==null) return null;	
		Long id = repository.store(instance);
		return id;
	}
	
	/**
	 * Aggiornamento di una risorsa EmailAddressView
	 * @param instance l'oggetto EmailAddressView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(EmailAddressView instance) {
		if(instance.getID()==null) return false;
		Boolean result = repository.update(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa EmailAddressView
	 * @param instance l'oggetto EmailAddressView da rimpiazzare
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(EmailAddressView instance) {		
		Boolean result = repository.deleteInstance(instance);
		return result;
	}
	
	/**
	 * Cancellazione di una risorsa EmailAddressView dato il suo ID
	 * @param ID l'identificativo della risorsa
	 * @return true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean deleteByID(Long id) {		
		if(id==null) return false;
		Boolean result = repository.deleteByID(id);
		return result;
	}
	
	public EmailAddressView findByID(Long id) {
		EmailAddressView instance = repository.findByID(id);
		return instance;
	}
	
	public List<EmailAddressView> findAll() {
		return repository.findAll();
	}
	
	
	/*****************************************************************/
	/***************************FRONT_END*****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		EmailAddressView view = findByID(ID);
		if (view == null)	return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("EmailAddressView");
		model.addObject("title", "EmailAddress_" + String.valueOf(ID));

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
		model.setViewName("EmailAddressInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<EmailAddressView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("EmailAddressSearch");
		return model;
	}

}
