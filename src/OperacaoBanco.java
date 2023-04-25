import java.util.HashMap;
import java.util.Scanner;

public class OperacaoBanco {

    private RepositorioContas repositorio;
    private ContaCorrente contaOperacao;
    private ContaCorrente contaLogada;

    public OperacaoBanco(RepositorioContas repositorio) {
        this.repositorio = repositorio;
    }

    public void abrirConta() {
        Scanner s = new Scanner(System.in);

        System.out.println("\nVocê está abrindo uma nova conta com a Bradesco!\n");
        System.out.println("Informe seu Nome completo para continuar:");
        String nome = s.nextLine();
        System.out.println("Informe seu documento CPF:");
        String documento = s.nextLine();
        System.out.println("Informe seu numero de telefone:");
        String telefone = s.nextLine();
        System.out.println("Além disso precisamos de seu e-mail para deixa-lo sempre ciente das novidades:");
        String email = s.nextLine();
        boolean confirmação = false;
        String senha = "";
        System.out.println("Digite a Senha para sua nova Conta:");
        while (!confirmação) {
            senha = s.nextLine();
            System.out.println("Digite novamente sua Senha:");
            String senha2 = s.nextLine();
            if (senha.equals(senha2)) {
                confirmação = true;
            }else{
                System.out.println(senha + " | " +senha2);
                System.out.println("Senhas não são iguais, insira novamente: ");
            }
        }
        repositorio.abrirConta(nome, documento, telefone, email, senha);

    }

    public boolean login(int agencia, int numeroConta,int digito, String senha) {
        ContaCorrente conta = repositorio.getConta(agencia, numeroConta, digito);
        if (conta != null && conta.getSenha().equals(senha)) {
            contaLogada = conta;
            System.out.println("Logado");
            return true;
        } else {
            System.out.println("Não Logado");
            return false;
        }
    }

    public void sacar(double valor) {
        if (contaLogada != null) {
            contaLogada.sacar(valor);
            System.out.println(contaLogada.getSaldo());
            repositorio.atualizaLinhaJson(contaLogada);
            System.out.println(contaLogada.getSaldo());

        } else {
            System.out.println("No account logged in.");
        }
    }
    public void depositar(double valor) {
        if (contaLogada != null) {
            contaLogada.depositar(valor);
            System.out.println(contaLogada.getSaldo());
            repositorio.atualizaLinhaJson(contaLogada);
            System.out.println(contaLogada.getSaldo());
        } else {
            System.out.println("No account logged in.");
        }
    }
    public void transferir(int agenciaDestino, int contaDestino, int digitoDestino, double valor) {
        ContaCorrente contaOrigem = contaLogada;
        ContaCorrente contaOperacao = repositorio.getConta(agenciaDestino, contaDestino, digitoDestino);
        if (contaOperacao == null) {
            System.out.println("Conta de destino não encontrada");
            return;
        }
        if (contaOrigem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente");
            return;
        }
        contaOrigem.debitar(valor);
        contaOperacao.creditar(valor);
        repositorio.atualizaLinhaJson(contaLogada);
        repositorio.atualizaLinhaJson(contaOperacao);

        System.out.println("Transferência realizada com sucesso");
    }
}

