package tdd;

import java.math.BigDecimal;
import java.time.Month;

import tdd.valor.ValorIluminacaoPublica;
import tdd.valor.ValorImposto;
import tdd.valor.ValorServico;

public class Conta {

	private final int consumo;
	private final Month mes;

	private BigDecimal valorPago;

	public Conta(int consumo, Month mes) {
		this.consumo = consumo;
		this.mes = mes;
		
		ValorServico valorServico = new ValorServico(consumo);
		valorServico
		.setNext(new ValorIluminacaoPublica())
		.setNext(new ValorImposto());
		
		this.valorPago = valorServico.getValor();
	}
	
	public BigDecimal getValor() {
		return valorPago;
	}

	public int getConsumo() {
		return consumo;
	}

	public Month getMes() {
		return mes;
	}
	
}
