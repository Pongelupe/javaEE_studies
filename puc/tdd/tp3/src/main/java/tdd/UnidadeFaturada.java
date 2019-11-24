package tdd;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

public class UnidadeFaturada {

	private Map<Month, Conta> contaPorMes = new EnumMap<>(Month.class);

	public Conta gerarContaMensal(Month mes, int consumo) {
		Conta conta = new Conta(consumo, mes);
		contaPorMes.put(mes, conta);
		return conta;
	}

	public Conta getContaMensal(Month mes) {
		return contaPorMes.get(mes);
	}
}
