package ar.edu.iua.iw3.negocio.service;

import java.util.List;
import java.util.Optional;


import ar.edu.iua.iw3.modelo.Historico.HistoricoDTO;
import ar.edu.iua.iw3.util.MensajeRespuesta;
import ar.edu.iua.iw3.util.RespuestaGenerica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.modelo.Historico.Historico;
import ar.edu.iua.iw3.modelo.Historico.HistoricoRepository;


@Service
public class HistoricoBusiness implements IHistoricoBusiness{

	private Logger log = LoggerFactory.getLogger(HistoricoBusiness.class);

	@Autowired
	private HistoricoRepository historicoDAO;
	
	@Override
	public Historico load(Long id) throws NoEncontradoException, NegocioException {
		Optional<Historico> op;
		try {
			op =  historicoDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new NegocioException(e);
		}
		if (!op.isPresent()) {
			throw new NoEncontradoException("El historico no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Historico> list() throws NegocioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaGenerica<Historico> add(Historico historico) throws NegocioException {
		MensajeRespuesta m=new MensajeRespuesta();
		try {
			historicoDAO.save(historico);
			m.setMensaje(historico.toString());
			return new RespuestaGenerica<Historico>(historico, m);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new NegocioException(e);
		}
	}

	@Override
	public Historico update(Historico historico) throws NoEncontradoException, NegocioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespuestaGenerica<Historico> delete(Long id) throws NegocioException, NoEncontradoException {
		MensajeRespuesta m=new MensajeRespuesta();
		Historico h = load(id);
		if(h == null)
			throw new NoEncontradoException("El historico que desea eliminar no se encuentra registrado");
		try {
			historicoDAO.deleteById(id);
			return new RespuestaGenerica<Historico>(h, m);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new NegocioException(e);
		}
		
	}

	@Override
	public HistoricoDTO loadLast() throws NoEncontradoException, NegocioException {

		Optional<Historico> op;
		try {
			op = historicoDAO.findLast();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new NegocioException(e);
		}
		if (!op.isPresent()) {
			throw new NoEncontradoException("No hay ordenes cargadas en la BD");
		}
	
		return new HistoricoDTO(op.get());
	}

}
