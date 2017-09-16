package br.com.flavia.financeiro;

import java.text.DecimalFormat;

public class ContaEspecial extends Conta {

    private double limite;
    DecimalFormat df = new DecimalFormat("#.##");

    public ContaEspecial(int ID, double saldo, double limite) {
        super(ID, saldo);
        this.limite = limite;
    }

    public boolean realizaSaque(double saque) {
        if (saque > (this.saldo + this.limite)) {
            extrato.add("SAQUE INDISPONÍVEL NO MOMENTO!\n");
            return false;
        } else {
            if (this.saldo < saque) {
                saque = (this.saldo - saque);
                this.saldo = 0;
                this.limite = (this.limite - saque);
                extrato.add("Saque         (-): " + "R$" + df.format(saque) + "\n");
            } else {
                this.saldo = (this.saldo - saque);
                extrato.add("Saque         (-): " + "R$" + df.format(saque) + "\n");
            }
            return true;
        }
    }

    public double getSaldo() {
        return this.saldo + this.limite;
    }
}
