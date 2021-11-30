package it.federicoRaimondi.gestionale.personservice.baseControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.List;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

/**
 *<p>
 *Interfaccia con i metodi per estrarre le informazioni dalla chiamata Http
 *proveniente dal client
 *</p> 
 * @author Federico Raimondi
 *
 */
@RestController
public class BaseController {

	@SuppressWarnings("unused")
	static final Logger logger = Logger.getLogger(BaseController.class.getName());

	/**
	 * Metodo generico per inviare una risposta al client
	 * 
	 * @param response   un oggetto/primitiva
	 * @param loggerInfo una messaggio da scrivere nel log
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> getResponse(Object response, String loggerInfo) {
		if (response instanceof MyResponseStatus) {
			return getResponseWithStatus((MyResponseStatus) response, loggerInfo);
		} else if (response instanceof Map) {
			return getResponseWithMap((Map<String, Object>) response, loggerInfo);
		}else if (response instanceof List) {
			return getResponseWithList((List<Object>) response, loggerInfo);
		} else if (response instanceof File) {
			InputStreamResource resource = null;
			try {
				resource = new InputStreamResource(new FileInputStream((File) response));
			} catch (FileNotFoundException e) {
				logger.warning("ERRORE in sendFileToNetwork");
				e.printStackTrace();
			}
			logger.info("invio file " + ((File) response).getName());

			return ResponseEntity.ok().contentLength(((File) response).length())
					.headers(getHeaders(((File) response).getName())).contentType(MediaType.MULTIPART_FORM_DATA)
					.body(resource);
		}
		logger.info(loggerInfo);
		return ResponseEntity.ok(response);
	}

	/**
	 * Metodo generico per inviare una risposta al client
	 * 
	 * @param response   un oggetto Json da inviare
	 * @param loggerInfo una messaggio da scrivere nel log
	 * @return
	 */
	public ResponseEntity<?> getResponseWithMap(Map<String, Object> response, String loggerInfo) {
		logger.info(loggerInfo);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Metodo generico per inviare una risposta al client
	 * 
	 * @param response   un oggetto Json da inviare
	 * @param loggerInfo una messaggio da scrivere nel log
	 * @return
	 */
	public ResponseEntity<?> getResponseWithList(List<Object> response, String loggerInfo) {
		logger.info(loggerInfo);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> getResponseWithStatus(MyResponseStatus status, String loggerInfo) {
		logger.info(loggerInfo);
		if (status == null) {
			return null;
		} else if (status == MyResponseStatus.BAD) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else if (status == MyResponseStatus.UNAUTHORIZED) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (status == MyResponseStatus.UNAUTHORIZED) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (status == MyResponseStatus.INTERNAL) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return null;
	}

	/**
	 * Preparazione dell'header per la response entity dei file scaricati
	 */
	public HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-disposition", "attachment; filename=" + fileName);
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

}
