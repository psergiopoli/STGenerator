package br.com.stgenerator.controle.entidades.DAO;

import java.io.Serializable;
import java.util.List;

import br.com.stgenerator.entidades.Carta;

public class CartasPaginado implements Serializable{
	
	private static final long serialVersionUID = 3277333499080997838L;
	private List<Carta> cartas;
	private Long numeroPaginas;
	
	public List<Carta> getCartas() {
		return cartas;
	}
	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	public Long getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Long numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

}
