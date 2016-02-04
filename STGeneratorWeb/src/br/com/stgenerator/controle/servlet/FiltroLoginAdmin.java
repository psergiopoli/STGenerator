package br.com.stgenerator.controle.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class FiltroLoginAdmin implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLoginAdmin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login")==null){		
			String nome = request.getParameter("name");
			String chave = request.getParameter("key");			
			
			if(nome == null || chave == null){
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			else if(nome.equals("admin") && chave.equals("admin")){
				session.setAttribute("login", "true");
			}else{
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
			}		
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
