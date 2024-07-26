package br.edu.iftm.leilao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.leilao.model.ItemDeLeilao;
import br.edu.iftm.leilao.model.Lance;

import br.edu.iftm.leilao.repository.ItemDeLeilaoRepository;


import java.util.Optional;

@RestController
@RequestMapping("/itemdeleilao")
public class ItemDeLeilaoController {

    @Autowired
    private ItemDeLeilaoRepository itemDeLeilaoRepository;
   

    @PostMapping
    public ResponseEntity<ItemDeLeilao> novo(@RequestBody ItemDeLeilao itemDeLeilao) {
        return ResponseEntity.ok(itemDeLeilaoRepository.save(itemDeLeilao));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ItemDeLeilao> atualiza(@PathVariable Long id, @RequestBody ItemDeLeilao itemDeLeilao) {
        Optional<ItemDeLeilao> optionalItem = itemDeLeilaoRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemDeLeilao atualItem = optionalItem.get();
            atualItem.setNome(itemDeLeilao.getNome());
            atualItem.setValorMinimo(itemDeLeilao.getValorMinimo());
            atualItem.setLeilaoAberto(itemDeLeilao.isLeilaoAberto());
            return ResponseEntity.ok(itemDeLeilaoRepository.save(atualItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemDeLeilaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<ItemDeLeilao>> lances() {
        return ResponseEntity.ok(itemDeLeilaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDeLeilao> lance(@PathVariable Long id) {
        return itemDeLeilaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/lance")
    public ResponseEntity<Void> adicionarLance(@PathVariable Long id, @RequestBody Lance lance) {
        Optional<ItemDeLeilao> optionalItem = itemDeLeilaoRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemDeLeilao item = optionalItem.get();
            item.adicionarLance(lance.getParticipante(), lance.getValor());
            itemDeLeilaoRepository.save(item);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<ItemDeLeilao> finalizar(@PathVariable Long id) {
        Optional<ItemDeLeilao> optionalItem = itemDeLeilaoRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemDeLeilao item = optionalItem.get();
            item.finalizar();
            itemDeLeilaoRepository.save(item);
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
