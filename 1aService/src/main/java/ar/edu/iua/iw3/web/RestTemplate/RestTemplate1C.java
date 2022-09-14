package ar.edu.iua.iw3.web.RestTemplate;

import ar.edu.iua.iw3.modelo.DTORestTemplate.Historico;
import ar.edu.iua.iw3.modelo.DTORestTemplate.UltimoHistorico;
import ar.edu.iua.iw3.util.MensajeRespuesta;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;


@RestController
public class RestTemplate1C {

    private Logger log = LoggerFactory.getLogger(RestTemplate1C.class);


    @Autowired
    org.springframework.web.client.RestTemplate restTemplate;


    public UltimoHistorico getHistoricoUltimo(String identificador) {
        String url = "http://localhost:8091/historico/last/".concat(String.valueOf(identificador));
        UltimoHistorico forObject = restTemplate.getForObject(url,UltimoHistorico.class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return forObject;
    }
    public List<Historico> getByCategory(String categoria,String order,String pageSize,String page) {
        String url = "http://localhost:8091/historico/order-by-category/".concat(String.valueOf(categoria)).concat("/"+(order)+"/"+pageSize+"/"+page);
        Historico[] forObject = restTemplate.getForObject(url,Historico[].class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return  Arrays.asList(forObject);
    }
    public List<Historico> getBySubcategory(String categoria,String subcategoria,String order,String pageSize,String page) {
        String url = "http://localhost:8091/historico/order-by-subcategory/".concat(String.valueOf(categoria)).concat("/"+(subcategoria)).concat("/"+(order)+"/"+pageSize+"/"+page);
        Historico[] forObject = restTemplate.getForObject(url,Historico[].class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return  Arrays.asList(forObject);
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