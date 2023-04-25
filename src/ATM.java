import java.util.Scanner;

public class ATM {
    private OperacaoBanco operacao;

    public ATM(OperacaoBanco operacao) {
        this.operacao = operacao;
    }

    public void telaBoasVindas() {
        System.out.println("Bem Vindo ao Banco Bradesco");
        System.out.println("\n");
        menuOpcoes();
    }

    public void menuOpcoes() {
        while (true) {
        System.out.println("Digite a opção:\n 1.Criar\n2.Logar");
        Scanner s = new Scanner(System.in);
        String opcao = s.nextLine();

            switch (opcao) {
                case "1":
                    operacao.abrirConta();
                    break;
                case "2":
                    telaSenha();
                    telaSaque();
                    break;
            }
        }
    }

    public void telaSenha() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nDigite a Agencia: ");
        int agencia = Integer.parseInt(s.nextLine());
        System.out.println("\nDigite a Conta: ");
        int conta = Integer.parseInt(s.nextLine());
        System.out.println("\nDigite o Digito: ");
        int digito = Integer.parseInt(s.nextLine());
        System.out.println("\nDigite sua Senha: ");
        String senha = s.nextLine();
        operacao.login(agencia,conta,digito,senha);
    }

    private void telaSaque() {

        Scanner s = new Scanner(System.in);
        System.out.println("Quanto quer sacar: ");
        double sacar = Double.parseDouble(s.nextLine());
        operacao.sacar(sacar);
        System.out.println("Realizado!");

    }

    public void telaTransferencia() {


    }
}
