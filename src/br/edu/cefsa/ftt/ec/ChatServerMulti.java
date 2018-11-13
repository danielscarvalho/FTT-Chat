package br.edu.cefsa.ftt.ec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//Source: https://pt.stackoverflow.com/questions/43127/multi-thread-em-socket-java

public class ChatServerMulti implements Runnable {
    
	public Socket cliente;

    public  ChatServerMulti(Socket cliente) {
        this.cliente = cliente;
    } //ChatServerMulti

    public static void main(String[] args)  throws IOException {     

        int porta = 3141;
    	
        ServerSocket servidor = new ServerSocket (porta);
        System.out.println("Porta " + porta + " aberta!");

        // Aguarda algu�m se conectar. A execu��o do servidor
        // fica bloqueada na chamada do m�todo accept da classe
        // ServerSocket. Quando algu�m se conectar ao servidor, o
        // m�todo desbloqueia e retorna com um objeto da classe
        // Socket, que � uma porta da comunica��o.
        
        System.out.println("Aguardando conex�o do cliente...");   

        while (true) {
        	
          Socket cliente = servidor.accept();
          // Cria uma thread do servidor para tratar a conex�o
          ChatServerMulti tratamento = new ChatServerMulti(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        
        } //while
        
        //servidor.close();
    } //main

    /* 
     * A classe Thread, que foi instancia no servidor, implementa Runnable.
     * Ent�o voc� ter� que implementar sua l�gica de troca de mensagens dentro deste m�todo 'run'.
     * 
     */
    
    public void run() {
    	
    	String clientIp = this.cliente.getInetAddress().getHostAddress();
        System.out.println("Nova conexao com o cliente " + clientIp);

        try {
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());

            //Exibe mensagem no console
            while(s.hasNextLine()){
                System.out.println(clientIp + " - " + new java.util.Date() + " - " + s.nextLine());
            }

            //Finaliza objetos
            s.close();
            this.cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        } //try
    } //run
} //ChatServerMulti
