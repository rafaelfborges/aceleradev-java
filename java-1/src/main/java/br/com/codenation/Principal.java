package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        DesafioMeuTimeApplication sistema = new DesafioMeuTimeApplication();
        LocalDate data = LocalDate.now();
        LocalDate d1 = LocalDate.parse("1939-12-09");
        LocalDate d2 = LocalDate.parse("1965-04-09");

        sistema.incluirTime(1L, "Brasil", data, "Amarela", "Azul");
        sistema.incluirTime(2L, "Argentina", data, "Azul", "Branco");
        sistema.incluirTime(3L, "Colombia", data, "Amarela", "Verde");

        /*sistema.incluirJogador(1L, 1L, "Ronaldinho", data, 95, BigDecimal.valueOf(10000));
        sistema.incluirJogador(2L, 1L, "Romário", d1, 100, BigDecimal.valueOf(5));
        sistema.incluirJogador(3L, 1L, "Bebeto", data, 92, BigDecimal.valueOf(1500));
        sistema.incluirJogador(4L, 1L, "Zinho", data, 80, BigDecimal.valueOf(15000));
        sistema.incluirJogador(5L, 1L, "Taffarel", d2, 75, BigDecimal.valueOf(3500));*/

        sistema.incluirJogador(-1L, 1L, "Dunga", data, 79, BigDecimal.valueOf(15000));
//        System.out.println(sistema.buscarCapitaoDoTime(2L));

        //sistema.definirCapitao(6L);
        /*System.out.println(sistema.buscarCapitaoDoTime(1L));
        System.out.println(sistema.buscarNomeJogador(1L));
        System.out.println(sistema.buscarNomeTime(1L));
        List<Long> teste = sistema.buscarJogadoresDoTime(1L);
        System.out.println(teste);

        System.out.println(sistema.buscarMelhorJogadorDoTime(1L));
        System.out.println(sistema.buscarTimes());
        System.out.println(sistema.buscarJogadorMaiorSalario(1L));

        System.out.println(sistema.buscarTopJogadores(5));
        System.out.println(sistema.buscarJogadorMaisVelho(1L));

        System.out.println(sistema.buscarCorCamisaTimeDeFora(1L, 2L));*/

    }
}
