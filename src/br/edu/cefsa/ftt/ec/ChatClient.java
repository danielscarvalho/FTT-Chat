package br.edu.cefsa.ftt.ec;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) 
            throws UnknownHostException, IOException {

		int porta = 3141;
		String host = "127.0.0.1";
		
		Socket cliente = new Socket(host, porta);
		System.out.println("O cliente se conectou ao servidor!");
		System.out.println(cliente.getInetAddress());
		
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());

		while (teclado.hasNextLine()) {
			saida.println(teclado.nextLine());
		}

		saida.close();
		teclado.close();
		cliente.close();
	}

}
