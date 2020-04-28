package br.com.codenation.repositories;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.models.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorRepository {
    private final List<Jogador> jogadores;

    public JogadorRepository() {
        this.jogadores = new ArrayList<Jogador>();
    }

    public void create(Jogador time){
        if(findId(time.getId()))
            throw new IdentificadorUtilizadoException("Identificador já utilizado.");
        jogadores.add(time);
    }

    public Jogador getById(Long id){
        return jogadores
                .stream()
                .filter(time -> time.getId().equals(id))
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
    }

    public List<Jogador> getAll() {
        return jogadores;
    }

    public Boolean findId(Long id){
        return jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id));
    }
}

