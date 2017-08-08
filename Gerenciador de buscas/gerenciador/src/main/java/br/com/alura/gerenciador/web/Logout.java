package br.com.alura.gerenciador.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Tarefa {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("usuario.logado");
		return "/WEB-INF/pages/logout.html";
	}

}
