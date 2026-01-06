// Define o pacote onde ficam as classes responsáveis
// por acessar o banco de dados (camada Repository)
package com.manoela.meusmapas.repository;

// Importa a entidade Mapa, que representa a tabela "mapa" no banco

import com.manoela.meusmapas.model.Mapa;

// Importa a interface JpaRepository do Spring Data JPA
// Ela já vem com vários métodos prontos para CRUD
import org.springframework.data.jpa.repository.JpaRepository;

// Indica ao Spring que esta interface é um componente de repositório
// Assim o Spring pode injetá-la automaticamente (@Autowired)
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Anotação que marca esta interface como um repositório Spring
// Não é obrigatória quando se usa JpaRepository,
// mas ajuda na clareza e organização do projeto
@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {
    // Busca um mapa pelo nome
    Optional<Mapa> findByNomeMapa(String nomeMapa);

    // Ao estender JpaRepository, temos automaticamente métodos como:
    // - findAll()        -> busca todos os mapas
    // - findById(id)     -> busca um mapa pelo ID
    // - save(mapa)      -> cria ou atualiza um mapa
    // - delete(mapa)    -> remove um mapa
    // - deleteById(id)  -> remove um mapa pelo ID

    // Não é necessário escrever nada aqui por enquanto,
    // pois o Spring Data JPA gera a implementação automaticamente
}
