package br.edu.iftm.leilao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.leilao.model.Participante;

@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {
	
}
