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

        System.out.println("Abrindo conta...");
        System.out.println("Digite seu Nome:");
        String nome = s.nextLine();
        System.out.println("Digite seu Documento:");
        String documento = s.nextLine();
        System.out.println("Digite seu Telefone:");
        String telefone = s.nextLine();
        System.out.println("Digite seu Email:");
        String email = s.nextLine();
        System.out.println("Digite sua Senha:");
        String senha = s.nextLine();
        repositorio.abrirConta(nome,documento,telefone,email,senha);
    }

    public void buscaContaPorNumero(int numero) {

    }

    public ContaCorrente login(int agencia, int numeroConta,int digito, String senha) {
        ContaCorrente conta = repositorio.getConta(agencia, numeroConta, digito);
        if (conta != null && conta.getSenha().equals(senha)) {
            contaLogada = conta;
            System.out.println("Logado");
            return conta;
        } else {
            System.out.println("Não Logado");
            return null;
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

    public void tranferir(ContaCorrente contaOperacao, double valor) {

    }

}

