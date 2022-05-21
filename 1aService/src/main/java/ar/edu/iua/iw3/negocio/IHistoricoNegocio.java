package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.util.RespuestaGenerica;

public interface IHistoricoNegocio {

    Historico buscarHistorico(Long id) throws NegocioException, NoEncontradoException;
    Historico buscarUltimoHistorico() throws NegocioException, NoEncontradoException;
    RespuestaGenerica<Historico> agregar(Historico historico) throws NegocioException;
    RespuestaGenerica<Historico> eliminar(Long id) throws NegocioException, NoEncontradoException;
}
