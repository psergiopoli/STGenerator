package br.com.stgenerator.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IpControl {

	private Map<String, Integer> contagem = new HashMap<String, Integer>();
	
	private Date last;

	public IpControl(){
		last = new Date();
	}
	
	public boolean add(String ip){
		if(contagem.containsKey(ip)){
			Integer count = contagem.get(ip);
			count++;
			contagem.replace(ip, count);
			return false;
		}else{
			contagem.put(ip, 1);
			return true;
		}
	}
	
	public Integer getNumeroCriados(String ip){
		if(contagem.containsKey(ip)){
			return contagem.get(ip);
		}else{
			return -1;
		}
	}
	
	public Map<String, Integer> getContagem() {
		return contagem;
	}

	public void setContagem(Map<String, Integer> contagem) {
		this.contagem = contagem;
	}

	public Date getLast() {
		return last;
	}

	public void setLast(Date last) {
		this.last = last;
	}
	
}
