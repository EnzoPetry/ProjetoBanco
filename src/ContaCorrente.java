import java.util.Date;

public class ContaCorrente {
    private double saldo;
    private int agencia;
    private int numeroConta;
    private int digito;
    private Date dataAbertura;
    private String senha;
    private Pessoa titular;

    public ContaCorrente(int agencia, int numeroConta, int digito, Pessoa titular, String senha) {
        this.saldo = 0.0;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.digito = digito;
        this.senha = senha;
        this.titular = titular;
        this.dataAbertura = new Date();
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

    public String getSenha() {
        return senha;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public boolean depositar(double valor) {
        saldo += valor;
        return true;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

}