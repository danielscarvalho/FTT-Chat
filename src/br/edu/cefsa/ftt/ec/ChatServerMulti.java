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

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        
        System.out.println("Aguardando conexão do cliente...");   

        while (true) {
        	
          Socket cliente = servidor.accept();
          // Cria uma thread do servidor para tratar a conexão
          ChatServerMulti tratamento = new ChatServerMulti(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        
        } //while
        
        //servidor.close();
    } //main

    /* 
     * A classe Thread, que foi instancia no servidor, implementa Runnable.
     * Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'.
     * 
     */
    
    public void run() {
    	
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try {
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());

            //Exibe mensagem no console
            while(s.hasNextLine()){
                System.out.println(s.nextLine());
            }

            //Finaliza objetos
            s.close();
            this.cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        } //try
    } //run
} //ChatServerMulti
