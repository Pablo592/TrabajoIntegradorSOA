package ar.edu.iua.iw3.negocio.service;

import java.util.List;

import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico.Historico;
import ar.edu.iua.iw3.util.RespuestaGenerica;


public interface IHistoricoBusiness {
	
	public Historico load(Long id) throws NoEncontradoException, NegocioException;

	public List<Historico> list() throws NegocioException;

	public RespuestaGenerica<Historico> add(Historico historico) throws NegocioException;

	public Historico update(Historico historico) throws NoEncontradoException, NegocioException;

	public RespuestaGenerica<Historico> delete(Long id) throws NoEncontradoException, NegocioException;

	public Historico loadLast()throws NoEncontradoException, NegocioException;
}
