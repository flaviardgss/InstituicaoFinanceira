package br.com.flavia.financeiro;

import java.io.Serializable;
import java.util.ArrayList;

public class Banco implements Serializable {

    private ArrayList<Conta> contas;
    private int currentID;

    public Banco() {
        this.contas = new ArrayList<>();
        this.currentID = 0;
    }
    
    public int numeroTotalDeContas(){
        return contas.size();
    }
    public Conta getConta(int ID) {
        return contas.get(ID);
    }

    public Conta novaConta(double saldo) {

        Conta conta = new Conta(contas.size(), saldo);
        contas.add(conta);
        return conta;

    }

    public Conta novaConta(double saldo, double limite) {
        ContaEspecial conta = new ContaEspecial(contas.size(), saldo, limite);
        contas.add(conta);
        return conta;
    }

    public boolean saque(int contaID, double saque) {
        Conta conta = contas.get(contaID);
        return conta.realizaSaque(saque);
    }

    public boolean deposito(int contaID, double deposito) {
        Conta conta = contas.get(contaID);
        return conta.realizaDeposito(deposito);
    }

    public boolean transferencia(int origemID, int destinoID, double valor) {
        Conta origem = contas.get(origemID);
        Conta destino = contas.get(destinoID);

        if (origem.realizaTransferencia(valor)) {
            return destino.creditaConta(valor);
        } else {
            return false;
        }
    }

    
    public void imprimir(int contaID) {
        Conta conta = contas.get(contaID);
        System.out.println("\tNúmero da conta (" + contaID + ")");
        System.out.println(conta.extrato);
    }
    
    public String obterExtrato(int contaID){
        Conta conta = contas.get(contaID);
        String extract = "";
        ArrayList<String> extrato = conta.extrato;
        
        for(String log: extrato){
            extract+= log + "#";
        }
        
        return extract;
    }
}
