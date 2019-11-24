package tdd.valor;

import java.math.BigDecimal;

public class ValorServico extends Valor {

	private static final BigDecimal TARIFA_FIXA = new BigDecimal(50);

	private final int consumo;

	public ValorServico(int consumo) {
		this.consumo = consumo;
	}

	@Override
	protected BigDecimal getValor(BigDecimal valor) {
		return valor.add(TARIFA_FIXA.multiply(new BigDecimal(consumo)));
	}

}
