package com.manoela.meusmapas.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mapa")
public class Mapa {

    // Chave primária do mapa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMapa;

    // Nome do mapa (obrigatório)
    @Column(nullable = false, length = 100)
    private String nomeMapa;

    // Data de criação preenchida automaticamente
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacaoMapa;

    // Um mapa pode ter vários pontos
    // Cascade ALL → pontos acompanham o mapa
    // orphanRemoval → ponto sem mapa é removido do banco
    @OneToMany(
            mappedBy = "mapaDoPonto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ponto> pontos = new ArrayList<>();

    public Mapa() {}

    public Mapa(String nomeMapa) {
        this.nomeMapa = nomeMapa;
    }

    public Integer getIdMapa() {
        return idMapa;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public void setNomeMapa(String nomeMapa) {
        this.nomeMapa = nomeMapa;
    }

    public List<Ponto> getPontos() {
        return pontos;
    }

    // Adiciona ponto e mantém o relacionamento sincronizado
    public void addPonto(Ponto ponto) {
        if (!pontos.contains(ponto)) {
            pontos.add(ponto);
            ponto.setMapaDoPonto(this);
        }
    }

    // Remove ponto e quebra o vínculo com o mapa
    public void removePonto(Ponto ponto) {
        if (pontos.remove(ponto)) {
            ponto.setMapaDoPonto(null);
        }
    }

    public Ponto orElseThrow(Object o) {
        return null;
    }
}
