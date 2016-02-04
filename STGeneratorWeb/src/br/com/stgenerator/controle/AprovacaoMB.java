package br.com.stgenerator.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.util.FacesUtil;

@ManagedBean(name="aprovacao")
@ViewScoped
public class AprovacaoMB extends Recentes implements Serializable{

	private static final long serialVersionUID = 5120606877089842789L;

	public AprovacaoMB() {
		
	}
	
	@PostConstruct
	private void init(){
		if(FacesUtil.getRequestParameter("page")==null){
			setPaginaAtual(1);
		}else{
			setPaginaAtual(new Integer(FacesUtil.getRequestParameter("page")));
		}
		setCartas(CartaDAO.getAprovacao(getPaginaAtual(),tamanhoPagina));
	}
	
	public void aprovarCarta(Integer id){
		CartaDAO.aprovarCarta(id,true);
		setCartas(CartaDAO.getAprovacao(getPaginaAtual(),tamanhoPagina));
	}
	
	public void reprovarCarta(Integer id){
		CartaDAO.aprovarCarta(id,false);
		setCartas(CartaDAO.getAprovacao(getPaginaAtual(),tamanhoPagina));
	}
	
	public void publicarCarta(Integer id){
		CartaDAO.publicarCarta(id,true);
		setCartas(CartaDAO.getAprovacao(getPaginaAtual(),tamanhoPagina));
	}
	
	public void privarCarta(Integer id){
		CartaDAO.publicarCarta(id,false);
		setCartas(CartaDAO.getAprovacao(getPaginaAtual(),tamanhoPagina));
	}
	
}
