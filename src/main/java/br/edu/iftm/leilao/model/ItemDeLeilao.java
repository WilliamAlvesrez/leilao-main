package br.edu.iftm.leilao.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ItemDeLeilao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valorMinimo;
    private boolean leilaoAberto;

    @OneToMany(mappedBy = "itemDeLeilao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lance> lancesRecebidos = new ArrayList<>();

    @OneToOne
    private Lance lanceVencedor;

    public ItemDeLeilao() {
        super();
    }

    public ItemDeLeilao(String nome, Double valorMinimo, boolean leilaoAberto) {
        super();
        this.nome = nome;
        this.valorMinimo = valorMinimo;
        this.leilaoAberto = leilaoAberto;
    }

    public void adicionarLance(Participante participante, Double valor) {
        if (this.leilaoAberto && valor >= this.valorMinimo) {
            Lance novoLance = new Lance(valor, participante);
            novoLance.setItemDeLeilao(this);
            this.lancesRecebidos.add(novoLance);
    
            if (this.lanceVencedor == null || valor > this.lanceVencedor.getValor()) {
                this.lanceVencedor = novoLance;
            }
        }
    }

    public void finalizar() {
        this.leilaoAberto = false;
    }
}
