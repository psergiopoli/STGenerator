package br.com.stgenerator.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.util.FacesUtil;

@ManagedBean(name="tops")
@ViewScoped
public class Tops extends Recentes implements Serializable{

	private static final long serialVersionUID = 899032746897920599L;
	
	public Tops() {
		
	}
	
	@PostConstruct
	private void init(){
		if(FacesUtil.getRequestParameter("page")==null){
			setPaginaAtual(1);
		}else{
			setPaginaAtual(new Integer(FacesUtil.getRequestParameter("page")));
		}
		setCartas(CartaDAO.getTops(getPaginaAtual(),tamanhoPagina));
	}
	
}
