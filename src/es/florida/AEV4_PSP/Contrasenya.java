package es.florida.AEV4_PSP;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable {
	String plano;
	String tipo;
	String encriptado;

	/**
	 * Constructor que hereda de la clase Serializable
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public Contrasenya() {
		super();
	}

	/**
	 * Método que encripta la contraseña en base a su tipo
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public void encriptar() {
		encriptado = "";
		if (this.tipo.equals("Normal")) {
			for (int i = 0; i < plano.length(); i++) {
				int ascii = (int) plano.charAt(i);
				if (ascii > 32) {
					encriptado += (char) (((int) plano.charAt(i)) + 1);
				} else {
					encriptado += "*";
				}
			}
		} else {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(plano.getBytes());
				BigInteger no = new BigInteger(1, messageDigest);
				encriptado = no.toString(16);
				while (encriptado.length() < 32) {
					encriptado = "0" + encriptado;
				}
			}
			catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
	}
	/**
	 * Método get de la contraseña en texto plano
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public String getPlainText() {
		return plano;
	}
	/**
	 * Método set de la contraseña en texto plano
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public void setPlainText(String nombre) {
		this.plano = nombre;
	}
	/**
	 * Método get de la contraseña encriptada
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public String getEncrypted() {
		return encriptado;
	}
	/**
	 * Método set de la contraseña encriptada
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public void setEncrypted(String edad) {
		this.encriptado = edad;
	}
	/**
	 * Método get del tipo de encriptacion
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Método set del tipo de encriptacion
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public void setTipo(Integer tipo) {
		this.tipo = tipo == 1 ? "Normal" : "MD5";
	}
}