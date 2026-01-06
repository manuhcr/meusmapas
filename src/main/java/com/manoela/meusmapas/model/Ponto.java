package com.manoela.meusmapas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pontos")
public class Ponto {

    // Chave primária do ponto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPonto;

    // Muitos pontos pertencem a um mapa
    // @JsonIgnore evita loop infinito no JSON
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mapaId")
    private Mapa mapaDoPonto;

    // Nome do ponto
    @Column(nullable = false, length = 180)
    private String nomePonto;

    // Coordenadas geográficas
    private BigDecimal latitudePonto;
    private BigDecimal longitudePonto;

    // Data de criação automática
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacaoPonto;

    public Ponto() {}

    public Ponto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public Integer getIdPonto() {
        return idPonto;
    }

    public Mapa getMapaDoPonto() {
        return mapaDoPonto;
    }

    // Define o mapa do ponto e mantém a relação dos dois lados
    public void setMapaDoPonto(Mapa mapaDoPonto) {
        this.mapaDoPonto = mapaDoPonto;

        if (mapaDoPonto != null && !mapaDoPonto.getPontos().contains(this)) {
            mapaDoPonto.addPonto(this);
        }
    }

    public String getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public BigDecimal getLatitudePonto() {
        return latitudePonto;
    }

    public void setLatitudePonto(BigDecimal latitudePonto) {
        this.latitudePonto = latitudePonto;
    }

    public BigDecimal getLongitudePonto() {
        return longitudePonto;
    }

    public void setLongitudePonto(BigDecimal longitudePonto) {
        this.longitudePonto = longitudePonto;
    }
}
