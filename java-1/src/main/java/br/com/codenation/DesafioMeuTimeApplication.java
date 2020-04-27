package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	List<Time> times = new ArrayList<Time>();
	List<Jogador> jogadores = new ArrayList<Jogador>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Validador.validarArgumentos(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		Validador.validarIdTime(id, times);

		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Validador.validarArgumentos(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		Validador.validarHabilidade(nivelHabilidade);
		Validador.validarSalario(salario);
		Validador.validarIdJogador(id, jogadores);

		buscarTime(idTime);
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		Jogador jogador = buscarJogador(idJogador);

		times.forEach(time -> {
			if(time.getId().equals(jogador.getIdTime())){
				time.setCapitaoTime(jogador.getId());
			}
		});
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		Time time = buscarTime(idTime);

		if(time.getCapitaoTime() == null)
			throw new CapitaoNaoInformadoException();

		return time.getCapitaoTime();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		Jogador jogador = buscarJogador(idJogador);

		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		Time time = buscarTime(idTime);

		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		return jogadores
				.stream()
				.filter(jogador -> {
					if(jogador.getIdTime().equals(idTime)) { return true; }
					throw new TimeNaoEncontradoException();
				})
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		Jogador jogador = jogadores
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparingInt(Jogador::getNivelHabilidade))
				.orElseThrow(JogadorNaoEncontradoException::new);

		return jogador.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Validador.validarArgumentos(idTime);
		Jogador jogador = jogadores
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getDataNascimento))
				.orElseThrow(TimeNaoEncontradoException::new);

		return jogador.getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times
				.stream()
				.map(Time::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Validador.validarArgumentos(idTime);
		Jogador jogador = jogadores
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getSalario))
				.orElseThrow(TimeNaoEncontradoException::new);

		return jogador.getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		Jogador jogador = buscarJogador(idJogador);

		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Validador.validarArgumentos(top);
		return jogadores
				.stream()
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
				.map(Jogador::getId)
				.limit(top)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Validador.validarArgumentos(timeDaCasa, timeDeFora);

		Time timeCasa = buscarTime(timeDaCasa);
		Time timeFora = buscarTime(timeDeFora);

		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
			return timeFora.getCorUniformeSecundario();
		return timeFora.getCorUniformePrincipal();
	}

	private Time buscarTime(Long id){
		return times
				.stream()
				.filter(time -> time.getId().equals(id))
				.findFirst()
				.orElseThrow(TimeNaoEncontradoException::new);
	}

	private Jogador buscarJogador(Long id){
		return jogadores
				.stream()
				.filter(jogador -> jogador.getId().equals(id))
				.findFirst()
				.orElseThrow(JogadorNaoEncontradoException::new);
	}
}
