package ar.edu.iua.iw3.modelo.Historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepository  extends JpaRepository<RawData, Long> {
}
