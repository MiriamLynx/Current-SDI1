package com.sdi.model;

public class Correo {

	private Integer id;
	private double fechahora;
	private String asunto;
	private String cuerpo;
	private int carpeta;
	private String login_Usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getFechahora() {
		return fechahora;
	}

	public void setFechahora(double d) {
		this.fechahora = d;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public int getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(int carpeta) {
		this.carpeta = carpeta;
	}

	public String getLogin_Usuario() {
		return login_Usuario;
	}

	public void setLogin_Usuario(String login_Usuario) {
		this.login_Usuario = login_Usuario;
	}

}
