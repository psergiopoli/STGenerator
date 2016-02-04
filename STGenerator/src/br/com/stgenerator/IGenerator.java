package br.com.stgenerator;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import br.com.stgenerator.util.Cores;
import br.com.stgenerator.util.ModeloCarta;

public class IGenerator {

	private InputStream arquivo = null;
	private BufferedImage img = null;
	private BufferedImage imgThumb = null;
	private Graphics2D img2d = null;
	
	private ModeloCarta modelo;
		
	public IGenerator(ModeloCarta modelo){
		this.modelo = modelo;
		if(modelo.numero==1){
			this.arquivo = modelo.lerImagem();
		}
		if(modelo.numero==2){
			this.arquivo = modelo.lerImagem();
		}
		try {
			this.img = ImageIO.read(arquivo);
			this.img2d = img.createGraphics();
		} catch (Exception e) {
			System.out.println("Modelo Inexistente");
			e.printStackTrace();
		}
	}
	
	public void drawOnImage(String valor,String cor,String fonte,int x, int y, int maxWidth){		
		Font font = new Font(fonte, Font.BOLD, modelo.tamanhoFonte);
		img2d.setFont(font);
		
		FontMetrics fontMetrics = img2d.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(valor);
		
	    for(int i = 0;stringWidth>maxWidth;i++){
	    	font = new Font(fonte, Font.BOLD, modelo.tamanhoFonte-i);
			img2d.setFont(font);
			fontMetrics = img2d.getFontMetrics();
			stringWidth = fontMetrics.stringWidth(valor);
	    }
		img2d.setColor(Cores.getColor(cor));
		img2d.drawString(valor, x, y);
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_WIDTH,int IMG_HEIGHT){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
	}
	
	public void escreveTitulo(String titulo,String cor,String fonte){		
		drawOnImage(titulo,cor, fonte, modelo.xTitulo, modelo.yTitulo, modelo.larguraMaximaTitulo);
	}
	
	public void escreveAtributo1(String nome, String valor,String cor, String fonte){
		drawOnImage(nome,cor, fonte, modelo.xAtributos,modelo.yAtributo1, modelo.larguraMaximaAtributos);
		drawOnImage(valor,cor, fonte, modelo.xValorAtributos, modelo.yAtributo1, modelo.larguraMaximaValorAtributos);
	}
	
	public void escreveAtributo2(String nome, String valor,String cor, String fonte){
		drawOnImage(nome,cor, fonte, modelo.xAtributos,modelo.yAtributo2, modelo.larguraMaximaAtributos);
		drawOnImage(valor,cor, fonte, modelo.xValorAtributos, modelo.yAtributo2, modelo.larguraMaximaValorAtributos);
	}
	
	public void escreveAtributo3(String nome, String valor,String cor, String fonte){
		drawOnImage(nome,cor, fonte, modelo.xAtributos,modelo.yAtributo3, modelo.larguraMaximaAtributos);
		drawOnImage(valor,cor, fonte, modelo.xValorAtributos, modelo.yAtributo3, modelo.larguraMaximaValorAtributos);
	}
	
	public void escreveAtributo4(String nome, String valor,String cor, String fonte){
		drawOnImage(nome,cor, fonte, modelo.xAtributos,modelo.yAtributo4, modelo.larguraMaximaAtributos);
		drawOnImage(valor,cor, fonte, modelo.xValorAtributos, modelo.yAtributo4, modelo.larguraMaximaValorAtributos);
	}
	
	public void escreveAtributo5(String nome, String valor,String cor, String fonte){
		drawOnImage(nome,cor, fonte, modelo.xAtributos,modelo.yAtributo5, modelo.larguraMaximaAtributos);
		drawOnImage(valor,cor, fonte, modelo.xValorAtributos, modelo.yAtributo5, modelo.larguraMaximaValorAtributos);
	}
	
	public void setImagemPrincipal(BufferedImage imgp){
		BufferedImage temp = resizeImage(imgp, imgp.getType(), modelo.larguraImagem, modelo.alturaImagem);
		img2d.drawImage(temp, null, modelo.xImagem, modelo.yImagem);
	}
	
	public void criarThumb(){
		imgThumb = resizeImage(img, img.getType(), img.getWidth()/3, img.getHeight()/3);
	}
	
	public void setImagemPrincipal(InputStream imgp) throws IOException{
		BufferedImage temp = ImageIO.read(imgp);
		temp = resizeImage(temp, temp.getType(), modelo.larguraImagem, modelo.alturaImagem);
		img2d.drawImage(temp, null, modelo.xImagem, modelo.yImagem);
	}
	
	public void setNumeracao(String cor, String numeracao){
		drawOnImage(numeracao,cor, "Times New Roman", modelo.xNumero, modelo.yNumero, modelo.larguraMaximaNumeracao);
	}

	public void setEmblemaST(){
		
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Graphics2D getImg2d() {
		return img2d;
	}

	public void setImg2d(Graphics2D img2d) {
		this.img2d = img2d;
	}

	public InputStream getArquivo() {
		return arquivo;
	}

	public void setArquivo(InputStream arquivo) {
		this.arquivo = arquivo;
	}

	public BufferedImage getImgThumb() {
		return imgThumb;
	}

	public void setImgThumb(BufferedImage imgThumb) {
		this.imgThumb = imgThumb;
	}

	public ModeloCarta getModelo() {
		return modelo;
	}

	public void setModelo(ModeloCarta modelo) {
		this.modelo = modelo;
	}
	
	
}
