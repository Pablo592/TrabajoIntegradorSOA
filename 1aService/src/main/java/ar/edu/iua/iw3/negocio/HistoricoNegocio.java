package ar.edu.iua.iw3.negocio;


import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.modelo.DTORestTemplate.UltimoHistorico;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.util.MensajeRespuesta;
import ar.edu.iua.iw3.util.RespuestaGenerica;
import ar.edu.iua.iw3.web.RestTemplate.RestTemplate1B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoNegocio implements IHistoricoNegocio{

    private Logger log = LoggerFactory.getLogger(HistoricoNegocio.class);

    @Autowired
    private RestTemplate1B rest1B;

    @Override
    public Historico buscarHistorico(Long id) throws NegocioException, NoEncontradoException {
        Optional<Historico> o = null;
        try {
                o = Optional.ofNullable(rest1B.getHistorico(id));
        } catch (Exception e) {
            if(o == null)
                throw new NoEncontradoException("No se encuentra al historico con id= " + id);
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o.get();
    }

    @Override
    public UltimoHistorico buscarUltimoHistorico(String identificador) throws NegocioException, NoEncontradoException {
        Optional<UltimoHistorico> o = null;
        try {

            o = Optional.ofNullable(rest1B.getHistoricoUltimo(identificador));
        } catch (Exception e) {
            if(o == null)
                throw new NoEncontradoException("No hay historicos cargados");
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o.get();
    }
    
    @Override
    public List<Historico> buscarPorCategoria(String categoria,String order) throws NegocioException, NoEncontradoException {
    	List<Historico> o = null;
        try {
            o = rest1B.getByCategory(categoria, order);
        } catch (Exception e) {
            if(o == null)
                throw new NoEncontradoException("No hay historicos cargados");
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o;
    }
    
    @Override
    public List<Historico> buscarPorSubcategoria(String categoria,String subcategoria,String order) throws NegocioException, NoEncontradoException {
        List<Historico> o = null;
        try {
            o = rest1B.getBySubcategory(categoria, subcategoria, order);
        } catch (Exception e) {
            if(o == null)
                throw new NoEncontradoException("No hay historicos cargados");
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o;
    }



    @Override
    public RespuestaGenerica<Historico> agregar(Historico historico) throws NegocioException{
            try {
                MensajeRespuesta mensajeRespuesta =  rest1B.addHistorico(historico);
                return new RespuestaGenerica<Historico>(historico, mensajeRespuesta);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }
        }

    @Override
    public RespuestaGenerica<Historico> eliminar(Long id) throws NegocioException, NoEncontradoException {
        MensajeRespuesta m=new MensajeRespuesta();
        Historico h = buscarHistorico(id);
        if(h == null)
            throw new NoEncontradoException("El usuario que desea eliminar no se encuentra registrado");
        try {
            rest1B.deleteHistorico(id);
            return new RespuestaGenerica<Historico>(h, m);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }
    }


