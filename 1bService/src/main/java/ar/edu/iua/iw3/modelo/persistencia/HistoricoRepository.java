package ar.edu.iua.iw3.modelo.persistencia;

import java.util.Optional;

import ar.edu.iua.iw3.modelo.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long>{


	@Query(value = "select * from historico order by id_historico desc limit 1;", nativeQuery = true)
	Optional<Historico> findLast();

}