package br.com.stgenerator.util;

import java.io.File;
import java.io.InputStream;

public enum ModeloCarta {
	Modelo1(1,50,80,115,560,78,540,430,95,600,678,761,844,923,630,55,90,0,0,530,389,90,155,"modelo1.jpg","jpg"),
	Modelo2(2,50,80,115,560,78,540,430,95,600,678,761,844,923,630,55,90,0,0,530,389,90,155,"modelo2.jpg","jpg"),
	Modelo3(2,50,80,115,560,78,540,430,95,600,678,761,844,923,630,55,90,0,0,530,389,90,155,"modelo3.png","png");
	
	public int numero;
	
	public int tamanhoFonte;
	
	public int xTitulo;
	public int yTitulo;
	public int larguraMaximaTitulo;
	
	public int xAtributos;
	public int xValorAtributos;
	
	public int larguraMaximaAtributos;
	public int larguraMaximaValorAtributos;
	
	public int yAtributo1;
	public int yAtributo2;
	public int yAtributo3;
	public int yAtributo4;
	public int yAtributo5;
	
	public int xNumero;
	public int yNumero;
	public int larguraMaximaNumeracao;
	
	public int xLogoST;
	public int yLogoST;
	
	public int larguraImagem;
	public int alturaImagem;
	public int xImagem;
	public int yImagem;
	
	private String nomeImagem = null;
	public String tipoImagem = null;
	
	ModeloCarta(int numero,int tamanhoFonte,
			int xTitulo,int yTitulo,int larguraMaximaTitulo,
			int xAtributos,int xValorAtributos,int larguraMaximaAtributos,int larguraMaximaValorAtributos,
			int yAtributo1,int yAtributo2,int yAtributo3,int yAtributo4,int yAtributo5,
			int xNumero,int yNumero,int larguraMaximaNumeracao,
			int xLogoST,int yLogoST,
			int larguraImagem,int alturaImagem,
			int xImagem,int yImagem,
			String nomeImagem,
			String tipoImagem){
		this.numero = numero;
		this.tamanhoFonte = tamanhoFonte;
		this.xTitulo = xTitulo;
		this.yTitulo = yTitulo;
		this.larguraMaximaTitulo = larguraMaximaTitulo;
		this.xAtributos = xAtributos;
		this.xValorAtributos = xValorAtributos;
		this.larguraMaximaAtributos = larguraMaximaAtributos;
		this.larguraMaximaValorAtributos = larguraMaximaValorAtributos;
		this.yAtributo1 = yAtributo1;
		this.yAtributo2 = yAtributo2;
		this.yAtributo3 = yAtributo3;
		this.yAtributo4 = yAtributo4;
		this.yAtributo5 = yAtributo5;
		this.xNumero = xNumero;
		this.yNumero = yNumero;
		this.larguraMaximaNumeracao = larguraMaximaNumeracao;
		this.xLogoST = xLogoST;
		this.yLogoST = yLogoST;
		this.larguraImagem = larguraImagem;
		this.alturaImagem = alturaImagem;
		this.xImagem = xImagem;
		this.yImagem = yImagem;
		
		this.nomeImagem = nomeImagem;
		this.tipoImagem = tipoImagem;
	}

	public File lerArquivo(){
		return new File("../imagens/"+nomeImagem);
	}
	
	public InputStream lerImagem() {
		return getClass().getResourceAsStream("../imagens/"+nomeImagem);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
