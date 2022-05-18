package ar.edu.iua.iw3.negocio;


import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.web.RestTemplate.RestTemplate1B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class HistoricoNegocio {

    private Logger log = LoggerFactory.getLogger(HistoricoNegocio.class);

    @Autowired
    private RestTemplate1B rest1B;

    public Historico buscarHistorico(Long id) throws NegocioException, NoEncontradoException {
        Optional<Historico> o = null;
        try {

                o = Optional.ofNullable(rest1B.getHistorico(id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!o.isPresent()) {
            throw new NoEncontradoException("No se encuentra al usuario con id= " + id);
        }
        return o.get();
    }
}
