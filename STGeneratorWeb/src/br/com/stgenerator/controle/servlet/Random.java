package br.com.stgenerator.controle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.entidades.Carta;

@WebServlet(name = "random",urlPatterns="/random")
public class Random extends HttpServlet{

	private static final long serialVersionUID = 5621941094281331648L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	List<Carta> cartas = CartaDAO.getCartasOrderByID();
	
	Integer inicio = cartas.get(0).getId();
	
	java.util.Random randomGenerator = new java.util.Random();
	
	Integer random = randomGenerator.nextInt((cartas.size()-inicio) + 1) + inicio;
		
	req.setAttribute("id", random.toString());
	req.setAttribute("from", "random");
	
	ServletContext context = this.getServletContext();
	RequestDispatcher dispatcher = context.getRequestDispatcher("/card.jsf");
	
	dispatcher.forward(req, resp);
	}	
}
