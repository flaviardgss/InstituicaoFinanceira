package br.com.flavia.servidor;

import br.com.flavia.financeiro.Banco;
import br.com.flavia.financeiro.Conta;

public class BancoServidor {

    private static BancoServidor instancia = null;
    private Banco banco;

    private BancoServidor() {
        banco = new Banco();
    }

    public static BancoServidor getInstance() {
        if (instancia == null) {
            instancia = new BancoServidor();
        }
        return instancia;
    }

    public Conta conta(int ID) {
        return banco.getConta(ID);
    }

    public Conta novaConta(double saldo) {
        return banco.novaConta(saldo);
    }

    public Conta novaConta(double saldo, double limite) {
        return banco.novaConta(saldo, limite);
    }

    public boolean saque(int contaID, double saque) {
        return banco.saque(contaID, saque);
    }

    public boolean deposito(int contaID, double deposito) {
        return banco.deposito(contaID, deposito);
    }

    public boolean transferencia(int origemID, int destinoID, double valor) {
        return banco.transferencia(origemID, destinoID, valor);
    }

    public String extrato(int contaID) {
        return banco.obterExtrato(contaID);
    }
    
    public int numeroTotalDeContas(){
        return banco.numeroTotalDeContas();
    }
    
    
}
