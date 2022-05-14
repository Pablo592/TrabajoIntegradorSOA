package ar.edu.iua.iw3.modelo.Historico;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.modelo.Cuentas.Usuario;
import ar.edu.iua.iw3.modelo.Historico.Historico;




@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long>{

	@Query(value = "select * from historico order by id_historico desc limit 1;", nativeQuery = true)
	Optional<Historico> findLast();

}
