package br.com.flavia.financeiro;

import java.util.Random;

public class Cliente implements Runnable {

    private Banco banco;
    private Conta conta;

    public Cliente(Banco banco, Conta conta) {
        this.banco = banco;
        this.conta = conta;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int operacao = random.nextInt(3);
            double valor = random.nextDouble() * 500;
            switch (operacao) {
                case 0:
                    banco.deposito(this.conta.getID(), valor);
                    break;
                case 1:
                    banco.saque(this.conta.getID(), valor);
                    break;
                case 2:
                    int destino = random.nextInt(300);
                    banco.transferencia(this.conta.getID(), destino, valor);
                    break;
            }
        }
    }
}
