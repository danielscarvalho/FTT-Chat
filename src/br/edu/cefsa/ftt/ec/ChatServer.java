package br.edu.cefsa.ftt.ec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

	public static void main(String[] args) throws IOException {
        
		int porta = 3141; //Pi
		
		ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Porta " + porta + " aberta!");

        Socket cliente = servidor.accept();
        System.out.println("Nova conex�o com o cliente " +     
            cliente.getInetAddress().getHostAddress());

        Scanner entrada = new Scanner(cliente.getInputStream());
        while (entrada.hasNextLine()) {
            System.out.println(entrada.nextLine());
        }

        entrada.close();
        servidor.close();
    }

}
