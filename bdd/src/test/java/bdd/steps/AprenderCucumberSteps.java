package bdd.steps;

import java.time.LocalDate;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AprenderCucumberSteps {

	/*
	 * @Given("que criei o arquivo corretamente") public void
	 * que_criei_o_arquivo_corretamente() { }
	 * 
	 * @When("executá-lo") public void executá_lo() { }
	 * 
	 * @Then("a especificação deve finalizar com sucesso") public void
	 * a_especificação_deve_finalizar_com_sucesso() { }
	 */

	@Dado("que criei o arquivo corretamente")
	public void que_criei_o_arquivo_corretamente() {
	}

	@Quando("executá-lo")
	public void executá_lo() {
	}

	@Então("a especificação deve finalizar com sucesso")
	public void a_especificação_deve_finalizar_com_sucesso() {
	}

	private Integer contador;
	private LocalDate entrega;

	@Dado("que o valor é {int}")
	public void que_o_valor_é(Integer int1) {
		contador = int1;
	}

	@Quando("eu incremetar em {int}")
	public void eu_incremetar_em(Integer int1) {
		contador += int1;
	}

	@Então("o valor do contador será {int}")
	public void o_valor_do_contador_será(Integer int1) {
		Assert.assertEquals(contador, int1);
	}

	@Dado("que a entrega é dia {int}\\/{int}\\/{int}")
	public void que_a_entrega_é_dia(Integer dia, Integer mes, Integer ano) {
		entrega = LocalDate.of(ano, mes, dia);
	}

	@Quando("a entrega atrasar em {int} dias")
	public void a_entrega_atrasar_em_dias(Integer dias) {
		entrega = entrega.plusDays(dias);
	}
	
	@Quando("a entrega atrasar em {int} meses")
	public void a_entrega_atrasar_em_meses(Integer meses) {
		entrega = entrega.plusMonths(meses);
	}

	@Então("a entrega será efetuada em {int}\\/{int}\\/{int}")
	public void a_entrega_será_efetuada_em(Integer dia, Integer mes, Integer ano) {
		Assert.assertEquals(entrega, LocalDate.of(ano, mes, dia));
	}
	
	@Dado("que o ticket é AF{int}")
	public void que_o_ticket_é_AF(Integer int1) {
	    throw new cucumber.api.PendingException();
	}

	@Dado("que o valor da passagem é R$ {double}")
	public void que_o_valor_da_passagem_é_R$(Double double1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Dado("que o nome do passageiro é {string}")
	public void que_o_nome_do_passageiro_é(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Dado("que o telefone do passageiro é {int}{int}")
	public void que_o_telefone_do_passageiro_é(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Quando("criar os steps")
	public void criar_os_steps() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Então("o teste vai funcionar")
	public void o_teste_vai_funcionar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}
