package br.edu.iftm.leilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.leilao.model.ItemDeLeilao;
import br.edu.iftm.leilao.model.Lance;
import br.edu.iftm.leilao.model.Participante;
import br.edu.iftm.leilao.repository.ItemDeLeilaoRepository;
import br.edu.iftm.leilao.repository.LanceRepository;
import br.edu.iftm.leilao.repository.ParticipanteRepository;

@Service
public class LanceService {

    @Autowired
    private ItemDeLeilaoRepository itemDeLeilaoRepository;

    @Autowired
    private LanceRepository lanceRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    public void adicionarLance(Long itemId, Long participanteId, Double valor) {
        ItemDeLeilao itemDeLeilao = itemDeLeilaoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item de leilão não encontrado"));
    
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado"));
    
        Lance lance = new Lance(valor, participante);
        lance.setItemDeLeilao(itemDeLeilao);
    
        lanceRepository.save(lance);
        itemDeLeilao.adicionarLance(participante, valor);
    
        itemDeLeilaoRepository.save(itemDeLeilao);
    }
}
