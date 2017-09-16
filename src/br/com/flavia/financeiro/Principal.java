package br.com.flavia.financeiro;

import java.util.Random;

public class Principal {

    public static void main(String[] args) {
        Banco instituicaoFinanceira = new Banco();
        Random rdm = new Random();
        try {
            for (int i = 0; i < 300; i++) {
                double saldoConta = rdm.nextDouble() * (3000 - 1000);
                double limiteConta = rdm.nextDouble() * (1000);
                if (rdm.nextBoolean()) { 
                    instituicaoFinanceira.novaConta(saldoConta);
                } else {
                    instituicaoFinanceira.novaConta(saldoConta, limiteConta);
                }
            }
            Thread threads[] = new Thread[300];
            for (int i = 0; i < 300; i++) {
                Conta conta = instituicaoFinanceira.getConta(i);

                Cliente cliente = new Cliente(instituicaoFinanceira, conta);
                Thread clienteThread = new Thread(cliente);
                clienteThread.start();
                threads[i] = clienteThread;
            }

            for (Thread thr : threads) {
                thr.join();
            }
            instituicaoFinanceira.imprimir(rdm.nextInt(300));
        } catch (Exception ex) {
            System.out.println("Erro na execução das Threads");
        }
    }
}
