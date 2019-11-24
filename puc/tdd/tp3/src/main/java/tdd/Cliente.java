package tdd;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cliente {

	private final String cpfCnpj;

	private List<UnidadeFaturada> unidadesFaturadas;

	public Cliente(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
		this.unidadesFaturadas = new ArrayList<>();
	}

	/**
	 * Primeira versão public boolean adicionarUnidadeFaturada(UnidadeFaturada
	 * unidadeFaturada) { return true; }
	 **/

	public boolean adicionarUnidadeFaturada(UnidadeFaturada unidadeFaturada) {
		return unidadeFaturada != null ? unidadesFaturadas.add(unidadeFaturada) : Boolean.FALSE;
	}

	public List<UnidadeFaturada> getUnidadesFaturadas() {
		return unidadesFaturadas;
	}

	/**
	 * Primeira Versao public BigDecimal getMaiorValorAPagar() { return null; }
	 */

	public BigDecimal getMaiorValorAPagar() {
		return getContas()
				.stream()
				.map(Conta::getValor)
				.max((arg0, arg1) -> arg0.compareTo(arg1))
				.orElse(BigDecimal.ZERO);
	}

	public List<Conta> getContas(Month... months) {
		return unidadesFaturadas.stream().map(unidade -> {
			List<Conta> contas = new ArrayList<>();
			for (Month mes : months) {
				contas.add(unidade.getContaMensal(mes));
			}
			return contas;
		}).flatMap(List<Conta>::stream).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public List<Conta> getContas() {
		return getContas(Month.values());
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

}
