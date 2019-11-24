package tdd.valor;

import java.math.BigDecimal;

public class ValorIluminacaoPublica extends Valor {

	public static final BigDecimal CUSTO = BigDecimal.valueOf(2000);

	/** Primeira Versão
	 * @Override protected BigDecimal getValor(BigDecimal valor) { return
	 *           CUSTO; }
	 */
	@Override
	protected BigDecimal getValor(BigDecimal valor) {
		return CUSTO.add(valor);
	}

}
