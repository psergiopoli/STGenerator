package br.com.stgenerator.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.controle.entidades.DAO.CartasPaginado;
import br.com.stgenerator.util.FacesUtil;

@ManagedBean(name="recentes")
@ViewScoped
public class Recentes implements Serializable{

	private static final long serialVersionUID = 899032746897920599L;

	private CartasPaginado cartas;
	
	private Integer paginaAtual = 1;
	
	protected final static Integer tamanhoPagina = 21;
	
	public Recentes() {
		
	}
	
	@PostConstruct
	private void init(){
		if(FacesUtil.getRequestParameter("page")==null){
			paginaAtual = 1;
		}else{
			paginaAtual = new Integer(FacesUtil.getRequestParameter("page"));
		}
		cartas = CartaDAO.getUltimasCartas(paginaAtual,tamanhoPagina);
	}
	
	public CartasPaginado getCartas() {
		return cartas;
	}

	public void setCartas(CartasPaginado cartas) {
		this.cartas = cartas;
	}

	public Integer getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public static Integer getTamanhopagina() {
		return tamanhoPagina;
	}
	
}
