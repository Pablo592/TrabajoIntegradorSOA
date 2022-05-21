package ar.edu.iua.iw3.web;

import ar.edu.iua.iw3.util.MensajeRespuesta;
import ar.edu.iua.iw3.util.RespuestaGenerica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import ar.edu.iua.iw3.negocio.service.IHistoricoBusiness;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico.Historico;
import springfox.documentation.spring.web.json.Json;




@RestController
@RequestMapping(value = "/historico")
public class HistoricoRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IHistoricoBusiness historicoBusiness;
	
	//---------Listar por ID------------------

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historico> load(@PathVariable("id") Long id) {
		try {

			return new ResponseEntity<Historico>(historicoBusiness.load(id), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<Historico>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Historico>(HttpStatus.NOT_FOUND);
		}
	}
	//---------Guardar Historico en BD------------------

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MensajeRespuesta> add(@RequestBody Historico historico) {
		try {
			MensajeRespuesta r =   historicoBusiness.add(historico).getMensaje();;
			return new ResponseEntity<MensajeRespuesta>(r, HttpStatus.CREATED);
		} catch (NegocioException e) {
			log.error(e.getMessage(), e);
			MensajeRespuesta r=new MensajeRespuesta(-1,e.getMessage());
			return new ResponseEntity<MensajeRespuesta>(r,HttpStatus.INTERNAL_SERVER_ERROR);
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

	//---------Borrar Historico ------------------


	@DeleteMapping(value="/{id}")
	public ResponseEntity<MensajeRespuesta> eliminar(@PathVariable("id") Long id) {
		try {
			MensajeRespuesta r =   historicoBusiness.delete(id).getMensaje();
			return new ResponseEntity<MensajeRespuesta>(r,HttpStatus.OK);
		} catch (NegocioException e) {
			log.error(e.getMessage(), e);
			MensajeRespuesta r=new MensajeRespuesta(-1,e.getMessage());
			return new ResponseEntity<MensajeRespuesta>(r,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			log.error(e.getMessage(), e);
			MensajeRespuesta r=new MensajeRespuesta(-1,e.getMessage());
			return new ResponseEntity<MensajeRespuesta>(r,HttpStatus.NOT_FOUND);
		}
	}
}
