package br.edu.iftm.leilao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.leilao.model.Lance;

@Repository
public interface LanceRepository extends CrudRepository<Lance, Long> {
	List<Lance> findByParticipanteId(Long id);// select * from lance l, participante p where p.id = l.participante.id?
	//todos os lances do participante com id = 117 e valor maior do que 100
	List<Lance> findByParticipanteIdAndValorGreaterThan(Long id, Double valor);
}
