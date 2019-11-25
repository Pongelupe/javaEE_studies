package tdd.valor;

import java.math.BigDecimal;

public abstract class Valor {

	private Valor next;
	private BigDecimal valor = BigDecimal.ZERO;

	protected abstract BigDecimal getValor(BigDecimal valor);

	public final BigDecimal getValor() {
		Valor temp = this;
		while (temp != null) {
			valor = temp.getValor(valor);
			temp = temp.getNext();
		}
		
		return valor;
	}

	private Valor getNext() {
		return next;
	}

	public Valor setNext(Valor valorChain) {
		this.next = valorChain;
		return next;
	}
}
