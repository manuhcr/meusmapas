package com.manoela.meusmapas.controller;

import com.manoela.meusmapas.model.Mapa;
import com.manoela.meusmapas.model.Ponto;
import com.manoela.meusmapas.service.MapaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller REST responsável pelas rotas de Mapas e Pontos
@RestController
@RequestMapping("/mapas")
public class MapaController {

    @Autowired
    private MapaService mapaService;

    // =============================
    // MAPAS
    // =============================

    // GET /mapas
    @GetMapping
    public List<Mapa> listarMapas() {
        return mapaService.listarMapas();
    }

    // GET /mapas/{idMapa}
    @GetMapping("/{idMapa}")
    public Mapa getMapa(@PathVariable Long idMapa) {
        return mapaService.buscarPorId(idMapa);
    }

    // GET /nome?nomeMapa=Zona Sul
    @GetMapping("/nome")
    public Mapa getMapaporNomeMapa(@RequestParam String nomeMapa) {
        return mapaService.buscarPorNomeMapa(nomeMapa);
    }


    // POST /mapas
    @PostMapping
    public Mapa criarMapa(@Valid @RequestBody Mapa mapa) {
        return mapaService.salvarMapaEPontos(mapa);
    }

    // PUT /mapas/{idMapa}
    @PutMapping("/{idMapa}")
    public Mapa atualizarMapa(
            @PathVariable Long idMapa,
            @Valid @RequestBody Mapa mapaAtualizado) {
        return mapaService.atualizarMapa(idMapa, mapaAtualizado);
    }

    // DELETE /mapas/{idMapa}
    @DeleteMapping("/{idMapa}")
    public ResponseEntity<String> deletarMapa(@PathVariable Long idMapa) {
        mapaService.deletarMapa(idMapa);
        return ResponseEntity.ok("Mapa deletado com sucesso!");
    }

    // DELETE /mapas/todos
    @DeleteMapping("/todos")
    public ResponseEntity<String> deletarTodosMapas() {
        String mensagem = mapaService.deletarTodosMapas();
        return ResponseEntity.ok(mensagem);
    }

    // =============================
    // PONTOS
    // =============================

    // GET /mapas/pontos
    @GetMapping("/pontos")
    public List<Ponto> listarPontos() {
        return mapaService.listarPontos();
    }

    // GET /mapas/{idMapa}/pontos/{idPonto}
    @GetMapping("/{idMapa}/pontos/{idPonto}")
    public Ponto getPontoPorId(@PathVariable Long idMapa, @PathVariable Long idPonto) {
        return mapaService.buscarPontoPorId(idMapa, idPonto);
    }

    // GET /mapas/pontos?nomeMapa=Zona Sul
    @GetMapping("/pontos/por-mapa")
    public List<Ponto> getPontosPorNomeMapa(@RequestParam String nomeMapa) {
        return mapaService.buscarPontosPorNomeMapa(nomeMapa);
    }


    // POST /mapas/{idMapa}/pontos
    @PostMapping("/{idMapa}/pontos")
    public Ponto adicionarPonto(
            @PathVariable Long idMapa,
            @Valid @RequestBody Ponto novoPonto) {
        return mapaService.adicionarPonto(idMapa, novoPonto);
    }

    // PUT /mapas/{idMapa}/pontos/{idPonto}
    @PutMapping("/{idMapa}/pontos/{idPonto}")
    public Ponto atualizarPonto(
            @PathVariable Long idMapa,
            @PathVariable Long idPonto,
            @Valid @RequestBody Ponto pontoAtualizado) {
        return mapaService.atualizarPonto(idMapa, idPonto, pontoAtualizado);
    }

    // DELETE /mapas/{idMapa}/pontos/{idPonto}
    @DeleteMapping("/{idMapa}/pontos/{idPonto}")
    public void deletarPonto(
            @PathVariable Long idMapa,
            @PathVariable Long idPonto) {
        mapaService.deletarPonto(idMapa, idPonto);
    }

    // DELETE /mapas/{idMapa}/pontos
    @DeleteMapping("/{idMapa}/pontos")
    public void deletarTodosPontos(@PathVariable Long idMapa) {
        mapaService.deletarTodosPontos(idMapa);
    }
}

