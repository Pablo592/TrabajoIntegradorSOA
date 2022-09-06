package ar.edu.iua.iw3.negocio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import ar.edu.iua.iw3.modelo.HistoricoDTO;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico;
import ar.edu.iua.iw3.util.RespuestaGenerica;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface IHistoricoBusiness {
	
	public Historico load(Long id) throws NoEncontradoException, NegocioException;
	public List<Historico> list() throws NegocioException, NoEncontradoException;
	public List<Historico> listByCategory(String category, String order) throws NegocioException, NoEncontradoException;
	public List<Historico> listBySubCategory(String category, String subcategory,String order) throws NegocioException, NoEncontradoException;
	public HistoricoDTO loadLastHistory() throws NoEncontradoException, NegocioException, JsonProcessingException;
	
}
