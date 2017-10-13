package br.com.flavia.cliente;

import br.com.flavia.servidor.Menu;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppCliente {

    public static void main(String[] args) {

        try {
            Socket sckCliente = new Socket("localhost", 10000);
            InputStream entrada = sckCliente.getInputStream();
            OutputStream saida = sckCliente.getOutputStream();
            Scanner leitor = new Scanner(entrada);
            Scanner teclado = new Scanner(System.in);
            PrintWriter escritor = new PrintWriter(saida);
            int opcao = 0;
            boolean flag = true;
            while (flag) {
                System.out.println(receberDados(leitor));
                opcao = teclado.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("[1] Conta normal");
                        System.out.println("[2] Conta especial");
                        int tipo = teclado.nextInt();
                        enviarDados(escritor, "CRIAR_CONTA" + "#" + tipo);
                        break;
                    case 2:
                        System.out.print("Número da Conta: ");
                        int numero = teclado.nextInt();
                        System.out.print("Valor: ");
                        double valor = teclado.nextDouble();
                        enviarDados(escritor, "SAQUE" + "#" + numero + "#" + valor);
                        break;
                    case 3:
                        System.out.print("Número da Conta: ");
                        numero = teclado.nextInt();
                        System.out.print("Valor: ");
                        valor = teclado.nextDouble();
                        enviarDados(escritor, "DEPOSITO" + "#" + numero + "#" + valor);
                        break;
                    case 4:
                        System.out.println("Transferir de: ");
                        int de = teclado.nextInt();
                        System.out.println("Transferir para: ");
                        int para = teclado.nextInt();
                        System.out.println("Valor: ");
                        valor = teclado.nextDouble();
                        enviarDados(escritor, "TRANSFERENCIA" + "#" + de + "#" + para + "#" + valor);
                        break;
                    case 5:
                        System.out.print("Número da Conta: ");
                        numero = teclado.nextInt();
                        enviarDados(escritor, "EXTRATO" + "#" + numero);
                        String extrato = leitor.nextLine();
                        String[] dados = extrato.split("#");
                        for (int i = 0; i < dados.length; i++) {
                            System.out.println(dados[i]);
                        }
                        break;
                    case 6:
                        enviarDados(escritor, "EXIBICAO");
                        String IDs = leitor.nextLine();
                        dados = IDs.split("#");
                        for (int i = 0; i < dados.length; i++) {
                            System.out.println("Número da conta: " + dados[i]);
                        }
                        break;
                    case 7:
                        System.out.println("Número da Conta: ");
                        numero = teclado.nextInt();
                        enviarDados(escritor, "SALDO" + "#" + numero);
                        String saldo = leitor.nextLine();
                        System.out.println("Saldo " + saldo + " da conta " + numero);
                        break;
                    case 0: 
                        enviarDados(escritor, "DESCONECTAR");
                        sckCliente.close();
                        System.out.println("Desconectando...");
                        flag = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        enviarDados(escritor, "MENU_PRINCIPAL");
                        break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(AppCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String receberDados(Scanner leitor) {
        String split[] = leitor.nextLine().split("#");
        String menu = "";

        for (int i = 0; i < split.length; i++) {
            menu += split[i] + "\n";
        }
        return menu;
    }

    public static void enviarDados(PrintWriter escritor, String dados) {
        escritor.println(dados);
        escritor.flush();
    }
}
