package br.com.stgenerator.controle.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.entidades.Carta;

@WebServlet(name = "thumb",urlPatterns="/thumb")
public class ImagensCartaThumb extends HttpServlet{

	private static final long serialVersionUID = 5621941094281331648L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String key = "";
		
		if(req.getParameter("id")==null){
			resp.setContentType("image/jpeg");
            resp.getOutputStream().write(getFotoDefault());
		}
		else{
			key = req.getParameter("id");
			try{
				Carta c = CartaDAO.getCarta(Integer.parseInt(key));
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(c.getThumb());
			}catch(Exception e){
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(getFotoDefault());
			}
		}
		
	}
	
	private byte[] getFotoDefault(){
		ServletContext context = getServletContext();
		InputStream input = context.getResourceAsStream("/images/indisponivel.jpg");
		
	    try {
	        return IOUtils.toByteArray(input);
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}  
	
}
