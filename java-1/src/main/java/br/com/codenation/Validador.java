package br.com.codenation;

import java.math.BigDecimal;
import java.util.List;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;

public class Validador {
    public static void validarArgumentos(Object... args) {
        for(Object obj : args) {
            if(obj == null) {
                throw new NullPointerException("Argumento nulo!");
            } else if(obj.toString().trim().isEmpty()){
                throw new IllegalArgumentException("Argumento vazio!");
            }
        }

    }

    public static void validarHabilidade(Integer habilidade){
        if(habilidade < 0 || habilidade > 100){
            throw new IllegalArgumentException("Habilidade do jogador tem que ser entre 0 e 100!");
        }
    }

    public static void validarSalario(BigDecimal salario){
        if(salario.signum() <= 0) {
            throw new IllegalArgumentException("Salário precisar ser maior que 0 e não pode ser negativo");
        }
    }

    public static void validarIdTime(Long id, List<Time> times){
        if(times.stream().anyMatch(time -> time.getId().equals(id)))
            throw new IdentificadorUtilizadoException("Identificador já utilizado: " + id);
    }

    public static void validarIdJogador(Long id, List<Jogador> jogadores){
        if(jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id)))
            throw new IdentificadorUtilizadoException("Identificador já utilizado: " + id);
    }
}
