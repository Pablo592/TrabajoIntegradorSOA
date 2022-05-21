package ar.edu.iua.iw3.web.RestController;

import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.negocio.HistoricoNegocio;
import ar.edu.iua.iw3.negocio.IHistoricoNegocio;
import ar.edu.iua.iw3.negocio.excepciones.BadRequest;
import ar.edu.iua.iw3.negocio.excepciones.EncontradoException;
import ar.edu.iua.iw3.web.RestTemplate.RestTemplate1B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;


@RestController
@RequestMapping(value = "/historico")
public class HistoricoRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IHistoricoNegocio historicoNegocio;
	
	//---------Listar por ID------------------

	@GetMapping(value = "/{id}")
	public ResponseEntity<Historico> load(@PathVariable("id") Long id) throws NegocioException, NoEncontradoException {
		try {
		Historico h = new Historico();
		return new ResponseEntity<Historico>(historicoNegocio.buscarHistorico(id), HttpStatus.OK);
	} catch (NegocioException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<Historico>(HttpStatus.INTERNAL_SERVER_ERROR);
	} catch (NoEncontradoException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<Historico>(HttpStatus.NOT_FOUND);
	}
}
	//---------Guardar Historico en BD------------------

	/*@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Historico historico) {
		try {
			System.out.println("historico:"+historico.toString());
			historicoBusiness.add(historico);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/historico"+ '/' + historico.getId_historico() );//
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (NegocioException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//---------Ultimo valor ------------------

	@GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historico> loadLasted() {

			try {
				return new ResponseEntity<Historico>(historicoBusiness.loadLast(), HttpStatus.OK);
			} catch (NegocioException e) {
				return new ResponseEntity<Historico>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NoEncontradoException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<Historico>(HttpStatus.NOT_FOUND);
			}
		}
*/
}
