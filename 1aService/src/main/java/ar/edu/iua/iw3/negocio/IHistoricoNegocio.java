package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

public interface IHistoricoNegocio {

    Historico buscarHistorico(Long id) throws NegocioException, NoEncontradoException;
}
