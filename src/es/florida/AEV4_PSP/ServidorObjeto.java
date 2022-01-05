package es.florida.AEV4_PSP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorObjeto {
	/**
	 * M�todo main que env�a y recibe peticiones al cliente
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		int numeroPuerto = 1234;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		System.err.println("SERVIDOR >> Escuchando...");
		Socket cliente = servidor.accept();
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		Contrasenya c = new Contrasenya();
		outObjeto.writeObject(c);
		System.err.println("SERVIDOR >> Envio a cliente objeto contrase�a... ");
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Contrasenya cMod = (Contrasenya) inObjeto.readObject();
		System.err.println("SERVIDOR >> Recibo de cliente: " + "\nContrase�a en texto plano: " + cMod.getPlainText() + "\nEncriptaci�n tipo: " + cMod.getTipo());
		System.err.println("SERVIDOR >> Encriptando contrase�a...");
		cMod.encriptar();
		outObjeto.writeObject(cMod);
		System.err.println("SERVIDOR >> Envio a cliente: " + "\n\tContrase�a en texto plano: " + cMod.getPlainText() + "\n\tEncriptaci�n tipo: " + cMod.getTipo() + "\n\tContrase�a encriptada:" + cMod.getEncrypted());
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
	}
}