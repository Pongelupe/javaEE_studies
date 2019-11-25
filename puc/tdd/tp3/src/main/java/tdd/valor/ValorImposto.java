package tdd.valor;

import java.math.BigDecimal;

public class ValorImposto extends Valor {
	
	public static final BigDecimal PORCENTAGEM_IMPOSTO = BigDecimal.valueOf(0.30);

	@Override
	protected BigDecimal getValor(BigDecimal valor) {
		return valor.add(PORCENTAGEM_IMPOSTO.multiply(valor));
	}

}
