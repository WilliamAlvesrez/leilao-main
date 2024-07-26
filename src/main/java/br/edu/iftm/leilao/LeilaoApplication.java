package br.edu.iftm.leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iftm.leilao.model.ItemDeLeilao;
import br.edu.iftm.leilao.model.Lance;
import br.edu.iftm.leilao.model.Participante;
import br.edu.iftm.leilao.repository.ItemDeLeilaoRepository;
import br.edu.iftm.leilao.repository.LanceRepository;
import br.edu.iftm.leilao.repository.ParticipanteRepository;

@SpringBootApplication
public class LeilaoApplication implements CommandLineRunner {

    @Autowired
    private ItemDeLeilaoRepository itemDeLeilaoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private LanceRepository lanceRepository;

    public static void main(String[] args) {
        SpringApplication.run(LeilaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Participante participante = new Participante("Participante 1", "123.456.789-00");
        participanteRepository.save(participante);

        ItemDeLeilao itemDeLeilao = new ItemDeLeilao("Item 1", 50.0, true);
        itemDeLeilaoRepository.save(itemDeLeilao);

        Lance lance = new Lance();
        lance.setValor(100.0);
        lance.setParticipante(participante);
        lance.setItemDeLeilao(itemDeLeilao);
        lanceRepository.save(lance);

        itemDeLeilao.setLanceVencedor(lance);
        itemDeLeilaoRepository.save(itemDeLeilao);
    }
}
