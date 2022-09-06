package ar.edu.iua.iw3.web;

import ar.edu.iua.iw3.modelo.HistoricoDTO;
import ar.edu.iua.iw3.util.MensajeRespuesta;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ar.edu.iua.iw3.negocio.service.IHistoricoBusiness;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico;


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
	
	//---------Ultimo valor ------------------

	@GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HistoricoDTO> loadLasted() {

			try {
				return new ResponseEntity<HistoricoDTO>(historicoBusiness.loadLastHistory(), HttpStatus.OK);
			} catch (NegocioException e) {
				return new ResponseEntity<HistoricoDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NoEncontradoException e) {
				return new ResponseEntity<HistoricoDTO>(HttpStatus.NOT_FOUND);
			} catch (JsonProcessingException e) {
				return new ResponseEntity<HistoricoDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	//---------Lista de Valores------------------

		@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Historico>> loadAll() {

				try {
					return new ResponseEntity<List<Historico>>(historicoBusiness.list(), HttpStatus.OK);
				} catch (NegocioException e) {
					return new ResponseEntity<List<Historico>>(HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (NoEncontradoException e) {
					return new ResponseEntity<List<Historico>>(HttpStatus.NOT_FOUND);
				}
		}
		
		//---------Lista de Valores------------------

		@GetMapping(value = "/order-by-category/{category}/{order}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Historico>> loadByCategory(@PathVariable("category") String category,@PathVariable("order") String order) {

				try {
					return new ResponseEntity<List<Historico>>(historicoBusiness.listByCategory(category,order), HttpStatus.OK);
				} catch (NegocioException e) {
					return new ResponseEntity<List<Historico>>(HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (NoEncontradoException e) {
					return new ResponseEntity<List<Historico>>(HttpStatus.NOT_FOUND);
				}
		}

	
}
