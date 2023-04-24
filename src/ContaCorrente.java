import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ContaCorrente {
    private double saldo;
    private int agencia;
    private int numeroConta;
    private int digito;
    private Date dataAbertura;
    private String senha;
    private Pessoa titular;
    private boolean logado;


    public ContaCorrente(int agencia, int numeroConta, int digito, Pessoa titular, String senha) {
        this.saldo = 0.0;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.digito = digito;
        this.senha = senha;
        this.titular = titular;
        this.dataAbertura = new Date();
        this.logado = false;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public int getDigito() {
        return digito;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public String getSenha() {
        return senha;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public boolean deposito(double valor) {
        this.saldo = this.saldo + valor;
        return true;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public double obterSaldo() {
        return saldo;
    }
}
