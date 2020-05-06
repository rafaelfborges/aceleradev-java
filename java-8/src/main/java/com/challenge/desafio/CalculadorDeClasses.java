package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Arrays;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object objeto) {
        return calcBigDecimal(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object objeto) {
        return calcBigDecimal(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object objeto) {
        return somar(objeto).subtract(subtrair(objeto));
    }

    private BigDecimal calcBigDecimal(Object objeto, Class<? extends Annotation> annotation){
        Class<?> classe = objeto.getClass();

        return Arrays.stream(classe.getDeclaredFields())
                .filter(field -> field.getType().equals(BigDecimal.class))
                .filter(field -> field.isAnnotationPresent(annotation))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (BigDecimal) field.get(objeto);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return BigDecimal.ZERO;
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
