package it.federicoRaimondi.gestionale.personservice.baseControllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Interfaccia per le chiamate sincrone tra servizi;
 * questo servizio necessita di effettuare chiamate ad un secondo servizio dell'applicazione (AuthService)
 * @author Raimondi Federico
 * */
@FeignClient(name="AuthService",url="localhost:8765/gateway/auth")
public interface AuthClient {
	
	@GetMapping(value="/api/checkJwt/{token}")
	public ResponseEntity<?> checkJwt(@PathVariable String token);

}