package br.com.codenation.utils;

import java.math.BigDecimal;

import br.com.codenation.models.Time;

public class Validador {
    public static void validarArgumentos(Object... args) {
        for(Object obj : args) {
            if(obj == null) {
                throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");
            } else if(obj.toString().trim().isEmpty()){
                throw new IllegalArgumentException("Argumento vazio!");
            }
        }

    }

    public static void validarHabilidade(Integer habilidade){
        if(habilidade < 0 || habilidade > 100){
            throw new IllegalArgumentException("Nível de habilidade deve estar entre 0 e 100");
        }
    }

    public static void validarSalario(BigDecimal salario){
        if(salario.signum() <= 0) {
            throw new IllegalArgumentException("Salário precisar ser maior que 0 e não pode ser negativo");
        }
    }
}
