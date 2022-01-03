package it.federicoRaimondi.gestionale.personservice.services;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import it.federicoRaimondi.gestionale.personservice.daoServices.AddressDAO;
import it.federicoRaimondi.gestionale.personservice.views.AddressView;

@Service
@Transactional
public class AddressService {

	private AddressDAO repository;

	public AddressService(AddressDAO repository) {
		this.repository = repository;
	}

	public String findByIdToJson(Long id) {
		AddressView instance = repository.findByID(id);
		if (instance == null) {
			return null;
		}
		return new Gson().toJson(instance);
	}

	/**
	 * Salvataggio di una risorsa AddressView
	 * 
	 * @param  instance l'oggetto AddressView da salvate nel DB
	 * @return          l'ID dell'oggetto appena salvato, null altrimenti
	 */
	public Long store(AddressView instance) {
		if (instance == null)
			return null;
		Long id = repository.store(instance);
		return id;
	}

	/**
	 * Aggiornamento di una risorsa AddressView
	 * 
	 * @param  instance l'oggetto AddressView da rimpiazzare
	 * @return          true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean update(AddressView instance) {
		if (instance.getID() == null)
			return false;
		Boolean result = repository.update(instance);
		return result;
	}

	/**
	 * Cancellazione di una risorsa AddressView
	 * 
	 * @param  instance l'oggetto AddressView da rimpiazzare
	 * @return          true se l'aggiornamento ? andato a buol fine, false altrimenti
	 */
	public Boolean delete(AddressView instance) {
		Boolean result = repository.deleteInstance(instance);
		return result;
	}

	/**
	 * Cancellazione di una risorsa AddressView dato il suo ID
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

	public AddressView findByID(Long id) {
		AddressView instance = repository.findByID(id);
		return instance;
	}

	public List<AddressView> findAll() {
		return repository.findAll();
	}

	/*****************************************************************/
	/*************************** FRONT_END *****************************/
	/*****************************************************************/

	public ModelAndView view(Long ID) {
		AddressView view = findByID(ID);
		if (view == null)
			return null;

		ModelAndView model = new ModelAndView();
		model.setViewName("AddressView");
		model.addObject("title", "Address_" + String.valueOf(ID));

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
		model.setViewName("AddressInput");
		return model;
	}

	public ModelAndView search() {
		ModelAndView model = new ModelAndView();
		List<AddressView> list = repository.findAll();
		model.addObject(list);
		model.setViewName("AddressSearch");
		return model;
	}

}
