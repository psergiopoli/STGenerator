package br.com.stgenerator.controle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.entidades.Carta;
import br.com.stgenerator.util.FacesUtil;

@ManagedBean(name="cartaMB")
@RequestScoped
public class CartaMB {
	
	private Carta carta;
	
	private Integer backPage;
	
	public CartaMB(){
		
	}
	
	@PostConstruct
	public void init(){
		if(FacesUtil.getRequestParameter("id")==null && FacesUtil.getRequest("id")==null){
			/* erro 404 ou 500 */
		}else if(FacesUtil.getRequestParameter("id")!=null){
			try {
				carta = CartaDAO.getCarta(Integer.parseInt(FacesUtil.getRequestParameter("id")));
			} catch (Exception e) {
				FacesUtil.sendRedirect("index.jsf");
				e.printStackTrace();
				return;
			}
			if(FacesUtil.getRequestParameter("from")!=null){
				if(FacesUtil.getRequestParameter("from").equals("random")){
					if(!carta.isPublico() || !carta.isAprovado()){
						FacesUtil.sendRedirect("random");
					}
				}
			}
			setView();
		}else if(FacesUtil.getRequest("id")!=null){
			try {
				carta = CartaDAO.getCarta(Integer.parseInt((String)FacesUtil.getRequest("id")));
			} catch (Exception e) {
				FacesUtil.sendRedirect("random");
				e.printStackTrace();
				return;
			}
			if(FacesUtil.getRequest("from")!=null){
				if(FacesUtil.getRequest("from").equals("random")){
					if(!carta.isPublico() || !carta.isAprovado()){
						FacesUtil.sendRedirect("random");
					}
				}
			}
			setView();
		}
	}
	
	public void setView(){		
		if(FacesUtil.getSession(carta.getId().toString())==null){								//Achar um jeito melhor, auahauuahhau
			CartaDAO.aumentaViewCarta(carta);												//Não mostrar pro usuario que aumentou assim que ele entrar , não é um erro xD
			FacesUtil.setSession(carta.getId().toString(), "true");
		}
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public Integer getBackPage() {
		return backPage;
	}

	public void setBackPage(Integer backPage) {
		this.backPage = backPage;
	}

}
