package br.com.stgenerator.controle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import br.com.stgenerator.IGenerator;
import br.com.stgenerator.controle.entidades.DAO.CartaDAO;
import br.com.stgenerator.entidades.Carta;
import br.com.stgenerator.util.Constantes;
import br.com.stgenerator.util.FacesUtil;
import br.com.stgenerator.util.IpControl;
import br.com.stgenerator.util.ModeloCarta;

@ManagedBean(name="index")
@ViewScoped
public class Index implements Serializable{

	private static final long serialVersionUID = -8621667656618751087L;

	private String tituloCarta;
	
	private boolean publico = false;
	
	private String atributo1;
	private String atributo2;
	private String atributo3;
	private String atributo4;
	private String atributo5;
	
	private String valorAtributo1;
	private String valorAtributo2;
	private String valorAtributo3;
	private String valorAtributo4;
	private String valorAtributo5;
	
	private String numeroCarta;
	
	private ModeloCarta modeloSelecionado;
	
	private List<ModeloCarta> modelos = new ArrayList<ModeloCarta>();
	
	private javax.servlet.http.Part imagem;
	
	private boolean captchaNeed = false;
	
	public Index(){
	}	
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init(){
		CartaDAO.init();
		modelos.add(ModeloCarta.Modelo1);
		modelos.add(ModeloCarta.Modelo2);
		modelos.add(ModeloCarta.Modelo3);
		
		String ip = FacesUtil.getHttpServletRequest().getRemoteAddr();
		
		if(FacesUtil.getApplication(Constantes.ipcontrol)!=null){
			IpControl control = (IpControl) FacesUtil.getApplication(Constantes.ipcontrol);
			Date atual = new Date();
			
			if(atual.getDay()!=control.getLast().getDay()){
				FacesUtil.removeApplication(Constantes.ipcontrol);
			}else if((atual.getHours()-control.getLast().getHours())>1){
				FacesUtil.removeApplication(Constantes.ipcontrol);
			}
		}
		
		if(FacesUtil.getApplication(Constantes.ipcontrol)!=null){
			IpControl control = (IpControl) FacesUtil.getApplication(Constantes.ipcontrol);
			if(control.getNumeroCriados(ip)>1){
				captchaNeed = true;
			}
		}
	}
	
	public String getCodigoHtmlRecaptcha() {
		ReCaptcha r = ReCaptchaFactory.newReCaptcha("6LeL5AgTAAAAAMv1vEKq1LttpVS9PKgtoZwCEMOD", "6LeL5AgTAAAAAHTVYdNtH5opFm2w4WPygjws1aZ4", false);
		Properties options = new Properties();
        options.setProperty("theme", "clean");
        options.setProperty("lang", "pt");
		return r.createRecaptchaHtml(null, options);
	}
	
	private boolean validaCaptcha(){
		if(!captchaNeed)
			return true;
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String enderecoRemoto = req.getRemoteAddr();
		
		ReCaptchaImpl r = new ReCaptchaImpl();
		r.setPrivateKey("6LeL5AgTAAAAAHTVYdNtH5opFm2w4WPygjws1aZ4");
		
		String textoCriptografado = (String) FacesUtil.getRequestParameter("recaptcha_challenge_field");
		String resposta = (String) FacesUtil.getRequestParameter("recaptcha_response_field");
		
		ReCaptchaResponse reCaptchaResponse = r.checkAnswer(enderecoRemoto, textoCriptografado, resposta);
		
		if(resposta.isEmpty() || !reCaptchaResponse.isValid()){
			return false;
		}		
		return true;
	}
	
	public String gerarCarta() throws IOException{		
		if(validaCaptcha()){		
			IGenerator i = new IGenerator(modeloSelecionado);
			
			i.escreveTitulo(tituloCarta,"black", "Times New Roman");
			i.escreveAtributo1(atributo1, valorAtributo1, "black", "Times New Roman");
			i.escreveAtributo2(atributo2, valorAtributo2, "black", "Times New Roman");
			i.escreveAtributo3(atributo3, valorAtributo3, "black", "Times New Roman");
			i.escreveAtributo4(atributo4, valorAtributo4, "black", "Times New Roman");
			i.escreveAtributo5(atributo5, valorAtributo5, "black", "Times New Roman");
			i.setNumeracao("black", numeroCarta);
			
			Carta c = new Carta();
			
			BufferedImage imgp = ImageIO.read(imagem.getInputStream());
			i.setImagemPrincipal(imgp);
			i.criarThumb();
			
			c.setTitulo(tituloCarta);
			c.setViews(1);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baosThumb = new ByteArrayOutputStream();
			
			if(i.getModelo().tipoImagem.equals("jpg")){
				ImageIO.write(i.getImg(), "jpg", baos);
				ImageIO.write(i.getImgThumb(), "jpg", baosThumb);
			}else if(i.getModelo().tipoImagem.equals("png")){
				ImageIO.write(i.getImg(), "png", baos);
				ImageIO.write(i.getImgThumb(), "png", baosThumb);
			}
			c.setImagem(baos.toByteArray());	
			c.setThumb(baosThumb.toByteArray());
			
			c.setPublico(publico);
			c.setAprovado(false);
			
			CartaDAO.inserirCarta(c);
			
			String ip = FacesUtil.getHttpServletRequest().getRemoteAddr();
			
			if(FacesUtil.getApplication(Constantes.ipcontrol)==null){
				IpControl control = new IpControl();
				control.add(ip);			
				FacesUtil.setApplication(Constantes.ipcontrol, control);
			}else{
				IpControl control = (IpControl) FacesUtil.getApplication(Constantes.ipcontrol);
				control.add(ip);			
				FacesUtil.setApplication(Constantes.ipcontrol, control);
			}			
			return "card.jsf?&id="+c.getId()+"&faces-redirect=true";
		}else{
			FacesUtil.addMessage("Captcha incorreto", null, Constantes.ERROR);
			return null;
		}
	}

	public String getTituloCarta() {
		return tituloCarta;
	}

	public void setTituloCarta(String tituloCarta) {
		this.tituloCarta = tituloCarta;
	}

	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

	public String getAtributo3() {
		return atributo3;
	}

	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}

	public String getAtributo4() {
		return atributo4;
	}

	public void setAtributo4(String atributo4) {
		this.atributo4 = atributo4;
	}

	public String getAtributo5() {
		return atributo5;
	}

	public void setAtributo5(String atributo5) {
		this.atributo5 = atributo5;
	}

	public String getValorAtributo1() {
		return valorAtributo1;
	}

	public void setValorAtributo1(String valorAtributo1) {
		this.valorAtributo1 = valorAtributo1;
	}

	public String getValorAtributo2() {
		return valorAtributo2;
	}

	public void setValorAtributo2(String valorAtributo2) {
		this.valorAtributo2 = valorAtributo2;
	}

	public String getValorAtributo3() {
		return valorAtributo3;
	}

	public void setValorAtributo3(String valorAtributo3) {
		this.valorAtributo3 = valorAtributo3;
	}

	public String getValorAtributo4() {
		return valorAtributo4;
	}

	public void setValorAtributo4(String valorAtributo4) {
		this.valorAtributo4 = valorAtributo4;
	}

	public String getValorAtributo5() {
		return valorAtributo5;
	}

	public void setValorAtributo5(String valorAtributo5) {
		this.valorAtributo5 = valorAtributo5;
	}

	public List<ModeloCarta> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarta> modelos) {
		this.modelos = modelos;
	}

	public ModeloCarta getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(ModeloCarta modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public javax.servlet.http.Part getImagem() {
		return imagem;
	}

	public void setImagem(javax.servlet.http.Part imagem) {
		this.imagem = imagem;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public boolean getCaptchaNeed() {
		return captchaNeed;
	}

	public void setCaptchaNeed(boolean captchaNeed) {
		this.captchaNeed = captchaNeed;
	}
}
