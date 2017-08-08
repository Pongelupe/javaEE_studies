package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/controladora")
public class Controladora extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String task = req.getParameter("tarefa");
		if (task == null)
			throw new IllegalArgumentException("Task null");
		task = "br.com.alura.gerenciador.web." + task;
		try {
			Class<?> tipo = Class.forName(task);
			Tarefa instancia = (Tarefa) tipo.newInstance();
			String pagina = instancia.execute(req, resp);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(pagina);
			requestDispatcher.forward(req, resp);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
