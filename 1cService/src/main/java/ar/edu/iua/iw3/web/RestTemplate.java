package ar.edu.iua.iw3.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.iua.iw3.modelo.Historico;


@RestController
public class RestTemplate {

    private Logger log = LoggerFactory.getLogger(RestTemplate.class);
    
    
    //@Autowired
    //org.springframework.web.client.RestTemplate restTemplate;
    private final org.springframework.web.client.RestTemplate restTemplate;


    @Autowired
    public RestTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    
    public Historico getHistorico(Long id) {
        String url = "http://localhost:8090/historico/".concat(String.valueOf(id));
        Historico forObject = restTemplate.getForObject(url, Historico.class);
        log.info("Result" + forObject);
        System.out.println(forObject.toString());
        return forObject;
    }
    public Object getHistoricosList() {
        String url = "http://localhost:8090/historico/all";
        Object forObject = restTemplate.getForObject(url,Object.class);
        log.info("Result" + forObject);
        return forObject;
    }   

}
