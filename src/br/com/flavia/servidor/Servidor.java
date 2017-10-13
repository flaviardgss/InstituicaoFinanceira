package br.com.flavia.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try {

            ServerSocket sckServer = new ServerSocket(10000);
            while (true) {
                Socket sckClient = sckServer.accept();
                SessaoCliente sessao = new SessaoCliente(sckClient);
                sessao.iniciarSessao();
                
            }

        } catch (IOException ex) {
            System.out.println("Nao foi possível realizar conexão!" + ex);
        }

    }
}
