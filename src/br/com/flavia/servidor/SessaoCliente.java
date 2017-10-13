package br.com.flavia.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessaoCliente implements Runnable {

    private Socket sckCliente;
    private Thread thr;
    private Scanner leitor;
    private InputStream entrada;
    private OutputStream saida;
    private PrintWriter escritor;
    private BancoServidor bancoServidor;

    @Override
    public void run() {
        while (true) {
            enviarDados(Menu.MenuPrincipal);
            String dados[] = receberDados().split("#");

            switch (dados[0]) {
                case "CRIAR_CONTA":
                    bancoServidor = BancoServidor.getInstance();
                    switch (dados[1]) {
                        case "1":
                            bancoServidor.novaConta(200);
                            break;
                        case "2":
                            bancoServidor.novaConta(200, 1000);
                            break;
                        default:
                            break;
                    }
                    break;

                case "SAQUE":
                    int numero = Integer.parseInt(dados[1]);
                    double valor = Double.parseDouble(dados[2]);
                    bancoServidor.saque(numero, valor);
                    break;

                case "DEPOSITO":
                    numero = Integer.parseInt(dados[1]);
                    valor = Double.parseDouble(dados[2]);
                    bancoServidor.deposito(numero, valor);
                    break;

                case "TRANSFERENCIA":
                    int de = Integer.parseInt(dados[1]);
                    int para = Integer.parseInt(dados[2]);
                    valor = Double.parseDouble(dados[3]);
                    bancoServidor.transferencia(de, para, valor);
                    break;
                case "EXTRATO":
                    numero = Integer.parseInt(dados[1]);
                    bancoServidor.extrato(numero);
                    enviarDados(bancoServidor.extrato(numero));
                    break;

                case "EXIBICAO":
                    String IDs = "";
                    for (int i = 0; i < bancoServidor.numeroTotalDeContas(); i++) {
                        IDs += bancoServidor.conta(i).getID() + "#";
                    }
                    enviarDados(IDs);
                    break;

                case "SALDO":
                    numero = Integer.parseInt(dados[1]);
                    String saldo = String.valueOf(bancoServidor.conta(numero).getSaldo());
                    enviarDados(saldo);
                    break;
                case "MENU_PRINCIPAL":
                    break;
                case "DESCONECTAR": {
                    try {
                        sckCliente.close();
                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(SessaoCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }

    public void iniciarSessao() {
        thr = new Thread(this);
        thr.start();
    }

    private void enviarDados(String dados) {
        escritor.println(dados);
        escritor.flush();
    }

    private String receberDados() {
        return leitor.nextLine();

    }

    public SessaoCliente(Socket cliente) {
        sckCliente = cliente;
        try {
            entrada = sckCliente.getInputStream();
            saida = sckCliente.getOutputStream();
            leitor = new Scanner(entrada);
            escritor = new PrintWriter(saida);

        } catch (IOException ex) {
            Logger.getLogger(SessaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
