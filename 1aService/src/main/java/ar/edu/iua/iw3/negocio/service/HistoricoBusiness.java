package ar.edu.iua.iw3.negocio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico.Historico;
import ar.edu.iua.iw3.modelo.Historico.HistoricoRepository;
import springfox.documentation.spring.web.json.Json;


@Service
public class HistoricoBusiness implements IHistoricoBusiness{
	@Autowired
	private HistoricoRepository historicoDAO;
	
	@Override
	public Historico load(Long id) throws NoEncontradoException, NegocioException {
		Optional<Historico> op;
		try {
			op =  historicoDAO.findById(id);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
		if (!op.isPresent()) {
			throw new NoEncontradoException("El Orden con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Historico> list() throws NegocioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Historico add(Historico historico) throws NegocioException {
		try {
			return historicoDAO.save(historico);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Historico update(Historico historico) throws NoEncontradoException, NegocioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws NoEncontradoException, NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Historico loadLast() throws NoEncontradoException, NegocioException {
		Optional<Historico> op;
		try {
			op =  historicoDAO.findLast();
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	
		return op.get();
	}

}
