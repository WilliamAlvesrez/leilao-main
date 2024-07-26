package br.edu.iftm.leilao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data // boilerplate
@Entity
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;

    @ManyToOne // um lance é feito por um participante
    private Participante participante;

    @ManyToOne
    @JoinColumn(name = "item_de_leilao_id")
    private ItemDeLeilao itemDeLeilao;

    // Construtor padrão
    public Lance() {
    }

    // Construtor com parâmetros
    public Lance(Double valor, Participante participante) {
        this.valor = valor;
        this.participante = participante;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
