package ar.edu.iua.iw3.negocio.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.iua.iw3.modelo.HistoricoDTO;

import ar.edu.iua.iw3.modelo.cache.Memcache;
import ar.edu.iua.iw3.util.MensajeRespuesta;
import ar.edu.iua.iw3.util.RespuestaGenerica;
import ar.edu.iua.iw3.web.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico;



@Service
public class HistoricoBusiness implements IHistoricoBusiness{

	private static String ultimo  = "ULTIMO";
	//private Memcache cache = new Memcache();
	private Logger log = LoggerFactory.getLogger(HistoricoBusiness.class);
	
	@Autowired
    private RestTemplate historicosRestTemplate;


	@Override
	 public Historico load(Long id) throws NegocioException, NoEncontradoException {
        Optional<Historico> o = null;
        try {
                o = Optional.ofNullable(historicosRestTemplate.getHistorico(id));
        } catch (Exception e) {
            if(o == null)
                throw new NoEncontradoException("No se encuentra al historico con id= " + id);
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o.get();
    }

	@Override
	public List<Historico> list() throws NegocioException, NoEncontradoException {
		List<Historico> o = null;
	        try {
	           o = historicosRestTemplate.getHistoricosList();
	        } catch (Exception e) {
	            if(o == null)
	                throw new NoEncontradoException("No se encuentra ninguna lista de historicos");
	            log.error(e.getMessage(), e);
	            throw new NegocioException(e);
	        }
	        return o;
	}

	@Override
	public HistoricoDTO loadLastHistory() throws NoEncontradoException, NegocioException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Historico> listByCategory(String category,String order) throws NegocioException, NoEncontradoException {
				
		List<Historico> orderResults = list();
		orderResults  = orderResults.stream().filter(p -> p.getCategoria().toLowerCase().equals(category.toLowerCase())).collect(Collectors.toList());
		
		if(order.equals("desc")) {
			Collections.sort(orderResults, (s1, s2) -> { return s1.getFechaHoraRecepcion().compareTo(s2.getFechaHoraRecepcion()); });
		}else {
			Collections.sort(orderResults, Collections.reverseOrder((s1, s2) -> { return s1.getFechaHoraRecepcion().compareTo(s2.getFechaHoraRecepcion());}));
		}
		
		return orderResults;
	}



}
