package br.com.flavia.financeiro;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Conta implements Serializable {

    protected int ID;
    protected double saldo;
    ArrayList<String> extrato = new ArrayList<String>();
    DecimalFormat df = new DecimalFormat("#.##");

    public Conta(int ID, double saldo) {
        this.ID = ID;
        this.saldo = saldo;
    }

    public boolean realizaSaque(double saque) {
        if (this.saldo >= saque) {
            this.saldo = (this.saldo - saque);
            extrato.add("Saque         (-): R$" + df.format(saque));
            return true;
        } else {
            extrato.add("SAQUE INDISPONÍVEL NO MOMENTO!");
            return false;
        }
    }

    public boolean realizaDeposito(double deposito) {
        if (deposito < 0) {
            return false;
        } else {
            this.saldo = (this.saldo + deposito);
            extrato.add("Depósito      (+): " + "R$" + df.format(deposito));
            return true;
        }
    }

    public boolean creditaConta(double deposito) {
        if (deposito < 0) {
            return false;
        } else {
            this.saldo = this.saldo + deposito;
            extrato.add("Transferência (+): " + "R$" + df.format(deposito));
            return true;
        }
    }

    public boolean realizaTransferencia(double saque) {
        if (this.saldo >= saque) {
            this.saldo = (this.saldo - saque);
            extrato.add("Transferência (-): " + "R$" + df.format(saque));
            return true;
        } else {
            return false;
        }
    }

    public int getID() {
        return this.ID;
    }

    public double getSaldo() {
        return this.saldo;
    }
}
