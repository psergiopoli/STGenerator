package br.com.stgenerator.controle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.com.stgenerator.util.ModeloCarta;

@WebServlet(name = "modelo carta",urlPatterns="/modelo")
public class ImagensModeloCarta extends HttpServlet{

	private static final long serialVersionUID = 5621941094281331648L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String key = req.getParameter("n");
		if(key.equals("1")){
			resp.setContentType("image/jpeg");
			resp.getOutputStream().write(IOUtils.toByteArray(ModeloCarta.Modelo1.lerImagem()));
		}
		else if(key.equals("2")){
			resp.setContentType("image/jpeg");
			resp.getOutputStream().write(IOUtils.toByteArray(ModeloCarta.Modelo2.lerImagem()));
		}
		else if(key.equals("3")){
			resp.setContentType("image/png");
			resp.getOutputStream().write(IOUtils.toByteArray(ModeloCarta.Modelo3.lerImagem()));
		}
		
	}
}
