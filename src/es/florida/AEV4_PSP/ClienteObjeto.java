package es.florida.AEV4_PSP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClienteObjeto {
	/**
	 * Método main que envía y recibe peticiones al servidor
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException {
		String host = "localhost";
		int puerto = 1234;
		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente = new Socket(host, puerto);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Contrasenya c = (Contrasenya) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor objeto contraseña...");
		System.out.println("Introduce la contraseña: ");
		Scanner sc= new Scanner(System.in);
		c.setPlainText(sc.nextLine());
		ObjectOutputStream cMod = new ObjectOutputStream(cliente.getOutputStream());
		List tipo = new ArrayList(Arrays.asList("1","2"));
		String t;
		do {
		System.out.println("¿Qué tipo de encriptación quieres?[1|2]\n1.Normal\n2.MD5");
		t=sc.nextLine();
		}while(!tipo.contains(t));
		c.setTipo(Integer.parseInt(t));
		cMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor: " + "\n\tContraseña en texto plano: " + c.getPlainText() + "\n\tEncriptación tipo: " + c.getTipo());
		
		c = (Contrasenya) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + "\n\tContraseña en texto plano: " + c.getPlainText() + "\n\tEncriptación tipo: " + c.getTipo() + "\n\tContraseña encriptada:" + c.getEncrypted());

		
		inObjeto.close();
		cMod.close();
		cliente.close();
	}
}