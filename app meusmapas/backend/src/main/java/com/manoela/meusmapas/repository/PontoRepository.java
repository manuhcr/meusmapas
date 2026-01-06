// Pacote responsável pela camada de acesso a dados (Repository)
// Aqui ficam as interfaces que conversam diretamente com o banco
package com.manoela.meusmapas.repository;

// Importa a entidade Ponto
// Essa classe representa a tabela "ponto" no banco de dados

import com.manoela.meusmapas.model.Mapa;
import com.manoela.meusmapas.model.Ponto;

// Importa o JpaRepository do Spring Data JPA
// Ele fornece métodos prontos para operações de banco (CRUD)
import org.springframework.data.jpa.repository.JpaRepository;

// Indica ao Spring que esta interface é um repositório
// Assim ele pode gerenciar essa classe e injetá-la onde for necessário
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {
    void deleteByMapaDoPonto(Mapa mapa);
    List<Ponto> findByMapaDoPonto_NomeMapa(String nomeMapa);

    // Ao estender JpaRepository, você ganha automaticamente os mesmos métodos de MapaRepository
}
