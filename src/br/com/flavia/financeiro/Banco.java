package br.com.flavia.financeiro;

public class Banco {

    private Conta[] contas;
    private int currentID;

    public Banco() {
        this.contas = new Conta[300];
        this.currentID = 0;
    }

    public Conta getConta(int ID) {
        if (ID < 300) {
            return this.contas[ID];
        } else {
            return null;
        }
    }

    public Conta novaConta(double saldo) {
        if (this.currentID == 300) {
            return null;
        } else {
            Conta conta = new Conta(this.currentID, saldo);
            this.contas[this.currentID] = conta;
            this.currentID += 1;
            return conta;
        }
    }

    public Conta novaConta(double saldo, double limite) {
        if (this.currentID == 300) {
            return null;
        } else {
            ContaEspecial conta = new ContaEspecial(this.currentID, saldo, limite);
            this.contas[this.currentID] = conta;
            this.currentID += 1;
            return conta;
        }
    }

    public boolean saque(int contaID, double saque) {
        Conta conta = this.contas[contaID];
        return conta.realizaSaque(saque);

    }

    public boolean deposito(int contaID, double deposito) {
        Conta conta = this.contas[contaID];
        return conta.realizaDeposito(deposito);
    }

    public boolean transferencia(int origemID, int destinoID, double valor) {
        Conta origem = this.contas[origemID];
        Conta destino = this.contas[destinoID];

        if (origem.realizaTransferencia(valor)) {
            return destino.creditaConta(valor);
        } else {
            return false;
        }
    }

    public void imprimir(int contaID) {
        Conta conta = this.contas[contaID];
        System.out.println("\tNúmero da conta (" + contaID + ")");
        System.out.println(conta.extrato);
    }
}
