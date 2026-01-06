// MapaService.java
package com.manoela.meusmapas.service;

import com.manoela.meusmapas.exception.MapaNotFoundException;
import com.manoela.meusmapas.exception.PontoNotFoundException;
import com.manoela.meusmapas.model.Mapa;
import com.manoela.meusmapas.model.Ponto;
import com.manoela.meusmapas.repository.MapaRepository;
import com.manoela.meusmapas.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

// Camada de serviço: concentra a lógica de negócio
@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private PontoRepository pontoRepository;

    // =============================
    // MAPA
    // =============================

    // Lista todos os mapas
    public List<Mapa> listarMapas() {
        return mapaRepository.findAll();
    }

    // Busca mapa por ID ou lança erro
    public Mapa buscarPorId(Long idMapa) {
        return mapaRepository.findById(idMapa)
                .orElseThrow(() -> new MapaNotFoundException(idMapa));
    }

    // Busca mapa por nome ou lança erro
    public Mapa buscarPorNomeMapa(String nomeMapa) {
        return mapaRepository.findByNomeMapa(nomeMapa)
                .orElseThrow(() -> new MapaNotFoundException(nomeMapa));
    }
    // Cria um mapa com seus pontos
    @Transactional
    public Mapa salvarMapaEPontos(Mapa mapa) {

        // Garante o vínculo correto mapa → pontos
        for (Ponto ponto : mapa.getPontos()) {
            ponto.setMapaDoPonto(mapa);
        }

        // Cascade salva mapa e pontos
        return mapaRepository.save(mapa);
    }

    // Atualiza dados básicos do mapa
    @Transactional
    public Mapa atualizarMapa(Long idMapa, Mapa mapaAtualizado) {
        Mapa mapa = buscarPorId(idMapa);
        mapa.setNomeMapa(mapaAtualizado.getNomeMapa());
        return mapaRepository.save(mapa);
    }

    // Remove mapa e seus pontos
    @Transactional
    public void deletarMapa(Long idMapa) {
        Mapa mapa = buscarPorId(idMapa);
        mapaRepository.delete(mapa);
    }

    // Remove todos os mapas
    @Transactional
    public String deletarTodosMapas() {
        List<Mapa> mapas = mapaRepository.findAll();

        if (mapas.isEmpty()) {
            return "Não há mapas para deletar.";
        }

        mapaRepository.deleteAll(); // deleta todos os mapas + pontos por cascade
        return "Todos os mapas foram deletados com sucesso!";
    }

    // =============================
    // PONTO
    // =============================

    // Lista todos os pontos
    public List<Ponto> listarPontos() {
        return pontoRepository.findAll();
    }

    // Busca ponto por ID ou lança erro
    public Ponto buscarPontoPorId(Long idMapa, Long idPonto) {
        return pontoRepository.findById(idPonto)
                .orElseThrow(() -> new PontoNotFoundException(idPonto));
    }

    // Busca pontos por mapa ou lança erro se não houver nenhum
    public List<Ponto> buscarPontosPorNomeMapa(String nomeMapa) {
        List<Ponto> pontos = pontoRepository.findByMapaDoPonto_NomeMapa(nomeMapa);

        if (pontos.isEmpty()) {
            throw new PontoNotFoundException(nomeMapa); // ou PontoNotFoundException se preferir
        }

        return pontos;
    }


    // Adiciona um ponto a um mapa existente
    @Transactional
    public Ponto adicionarPonto(Long idMapa, Ponto novoPonto) {
        Mapa mapa = buscarPorId(idMapa);

        novoPonto.setMapaDoPonto(mapa);
        mapa.addPonto(novoPonto);

        mapaRepository.save(mapa);
        return novoPonto;
    }

    // Atualiza dados de um ponto
    @Transactional
    public Ponto atualizarPonto(Long idMapa, Long idPonto, Ponto pontoAtualizado) {

        // Valida existência do mapa
        buscarPorId(idMapa);

        // Busca ponto direto no banco
        Ponto ponto = pontoRepository.findById(idPonto)
                .orElseThrow(() -> new PontoNotFoundException(idPonto));


        ponto.setNomePonto(pontoAtualizado.getNomePonto());

        if (pontoAtualizado.getLatitudePonto() != null) {
            ponto.setLatitudePonto(pontoAtualizado.getLatitudePonto());
        }

        if (pontoAtualizado.getLongitudePonto() != null) {
            ponto.setLongitudePonto(pontoAtualizado.getLongitudePonto());
        }

        return pontoRepository.save(ponto);
    }

    // Remove um ponto específico de um mapa
    @Transactional
    public void deletarPonto(Long idMapa, Long idPonto) {
        Ponto ponto = pontoRepository.findById(idPonto)
                .orElseThrow(() -> new PontoNotFoundException(idPonto));

        pontoRepository.delete(ponto);
    }

    // Remove todos os pontos de um mapa
    @Transactional
    public void deletarTodosPontos(Long idMapa) {
        Mapa mapa = buscarPorId(idMapa);
        pontoRepository.deleteByMapaDoPonto(mapa);

    }
}
