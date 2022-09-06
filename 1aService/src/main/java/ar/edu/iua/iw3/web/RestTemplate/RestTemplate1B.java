package ar.edu.iua.iw3.web.RestTemplate;

import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.modelo.DTORestTemplate.UltimoHistorico;
import ar.edu.iua.iw3.util.MensajeRespuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;


@RestController
public class RestTemplate1B {

    private Logger log = LoggerFactory.getLogger(RestTemplate1B.class);


    @Autowired
    org.springframework.web.client.RestTemplate restTemplate;


    public UltimoHistorico getHistoricoUltimo() {
        String url = "http://localhost:8091/historico/last";
        UltimoHistorico forObject = restTemplate.getForObject(url,UltimoHistorico.class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return forObject;
    }
    
    public Historico getHistorico(Long id) {
        String url = "http://localhost:8090/historico/".concat(String.valueOf(id));
        Historico forObject = restTemplate.getForObject(url, Historico.class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return forObject;
    }

    public MensajeRespuesta addHistorico(Historico historico) throws RestClientException{
      MensajeRespuesta mensaje =  new MensajeRespuesta();
      return restTemplate.postForObject("http://localhost:8090/historico/add",historico,mensaje.getClass());
    }

    public void deleteHistorico(Long id) throws RestClientException{
        restTemplate.delete("http://localhost:8090/historico/".concat(String.valueOf(id)));
    }
}
