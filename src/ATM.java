import java.util.Scanner;

import static java.lang.System.exit;

public class ATM {
    private OperacaoBanco operacao;
    private boolean logado;


    public ATM(OperacaoBanco operacao) {
        this.operacao = operacao;
    }

    public void telaBoasVindas() {
        System.out.println("###############################");
        System.out.println("#                             #");
        System.out.println("# Bem Vindo ao Banco Bradesco #");
        System.out.println("#                             #");
        System.out.println("###############################");
        System.out.println("\n");
        menuInicial();
    }

    public void menuInicial() {
        while (true) {
            System.out.println("Oque gostaria de fazer:\n\n1. Criar uma nova conta\n2. Realizar o Login\n3. Sair");
            Scanner s = new Scanner(System.in);
            String opcao = s.nextLine();

            switch (opcao) {
                case "1":
                    operacao.abrirConta();
                    break;
                case "2":
                    telaSenha();
                    if (logado == true) {
                        menuOpções();
                    }
                    break;
                case "3":
                    exit(0);
                    break;
            }
        }
    }

    public void menuOpções() {
        Scanner s = new Scanner(System.in);
        System.out.println("Oque deseja fazer: \n\n1. Sacar\n2. Depositar\n3. Tranferir");
        String opcao = s.nextLine();

        switch (opcao) {
            case "1":
                telaSaque();
                break;
            case "2":
                telaDeposito();
                break;
            case "3":
                telaTransferencia();
                break;
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
        if (operacao.login(agencia, conta, digito, senha)) {
            logado = true;
        }
    }

    private void telaSaque() {
        Scanner s = new Scanner(System.in);
        System.out.println("Quanto quer sacar: ");
        double sacar = Double.parseDouble(s.nextLine());
        operacao.sacar(sacar);
        System.out.println("Realizado!");

    }

    private void telaDeposito() {
        Scanner s = new Scanner(System.in);
        System.out.println("Quanto quer depositar: ");
        double depositar = Double.parseDouble(s.nextLine());
        operacao.depositar(depositar);
        System.out.println("Realizado!");

    }

    public void telaTransferencia() {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite a Agencia de destino: ");
        int agenciaDestino = Integer.parseInt(s.nextLine());
        System.out.println("Digite a Conta de destino: ");
        int contaDestino = Integer.parseInt(s.nextLine());
        System.out.println("Digite o Digito de destino: ");
        int digitoDestino = Integer.parseInt(s.nextLine());
        System.out.println("Quanto quer transferir: ");
        double transferir = Double.parseDouble(s.nextLine());
        operacao.transferir(agenciaDestino, contaDestino, digitoDestino, transferir);
        System.out.println("Transferência realizada!");

    }
}
