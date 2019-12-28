package tdd.valor;

import java.math.BigDecimal;

public abstract class Valor {

	private Valor next;
	private BigDecimal valorReal = BigDecimal.ZERO;

	protected abstract BigDecimal getValor(BigDecimal valor);

	public final BigDecimal getValor() {
		Valor temp = this;
		while (temp != null) {
			valorReal = temp.getValor(valorReal);
			temp = temp.getNext();
		}
		
		return valorReal;
	}

	private Valor getNext() {
		return next;
	}

	public Valor setNext(Valor valorChain) {
		this.next = valorChain;
		return next;
	}
}
