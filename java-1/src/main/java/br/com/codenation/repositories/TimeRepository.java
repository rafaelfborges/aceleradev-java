package br.com.codenation.repositories;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeRepository {
    private final List<Time> times;

    public TimeRepository() {
        this.times = new ArrayList<Time>();
    }

    public void create(Time time){
        if(findId(time.getId()))
            throw new IdentificadorUtilizadoException("Identificador já utilizado.");
        times.add(time);
    }

    public Time getById(Long id){
        return times
                .stream()
                .filter(time -> time.getId().equals(id))
                .findFirst()
                .orElseThrow(TimeNaoEncontradoException::new);
    }

    public List<Time> getAll() { return times; }

    public Boolean findId(Long id){
        return times.stream().anyMatch(jogador -> jogador.getId().equals(id));
    }
}
