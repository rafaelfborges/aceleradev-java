package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;
import br.com.codenation.repositories.JogadorRepository;
import br.com.codenation.repositories.TimeRepository;
import br.com.codenation.utils.Validador;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final TimeRepository times = new TimeRepository();
	private final JogadorRepository jogadores = new JogadorRepository();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Validador.validarArgumentos(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		times.create(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Validador.validarArgumentos(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		Validador.validarHabilidade(nivelHabilidade);
		Validador.validarSalario(salario);

		times.getById(idTime);;
		jogadores.create(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		Jogador jogador = jogadores.getById(idJogador);

		times.getAll().forEach(time -> {
			if(time.getId().equals(jogador.getIdTime())){
				time.setCapitaoTime(jogador.getId());
			}
		});
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		Time time = times.getById(idTime);

		if(time.getCapitaoTime() == null)
			throw new CapitaoNaoInformadoException();

		return time.getCapitaoTime();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		return jogadores.getById(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		return times.getById(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		times.getById(idTime);

		return jogadores
				.getAll()
				.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(Jogador::getId).sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Validador.validarArgumentos(idTime);
		times.getById(idTime);
		return jogadores
				.getAll()
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.min(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.map(Jogador::getId).get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Validador.validarArgumentos(idTime);
		times.getById(idTime);
		return jogadores
				.getAll()
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId))
				.map(Jogador::getId).get();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times
				.getAll()
				.stream()
				.map(Time::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Validador.validarArgumentos(idTime);
		times.getById(idTime);

		return jogadores
				.getAll()
				.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId))
				.map(Jogador::getId).get();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Validador.validarArgumentos(idJogador);
		return jogadores.getById(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Validador.validarArgumentos(top);
		return jogadores
				.getAll()
				.stream()
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.map(Jogador::getId)
				.limit(top)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Validador.validarArgumentos(timeDaCasa, timeDeFora);

		Time timeCasa = times.getById(timeDaCasa);
		Time timeFora = times.getById(timeDeFora);

		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
			return timeFora.getCorUniformeSecundario();
		return timeFora.getCorUniformePrincipal();
	}
}
