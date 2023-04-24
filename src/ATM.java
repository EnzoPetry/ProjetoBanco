import java.util.Scanner;

public class ATM {

    private Banco banco;
    private ContaCorrente contaCorrente;
    private ContaCorrente contaLogada;


    public ATM(Banco banco) {
        this.banco = banco;
        this.contaCorrente = contaCorrente;
        this.contaLogada = contaLogada;
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
        banco.abrirConta(nome,documento,telefone,email,senha);
    }
    public void logar(){
        Scanner s = new Scanner(System.in);
        System.out.println("Digite a Agencia: ");
        int agencia = Integer.parseInt(s.nextLine());
        System.out.println("Digite o Numero da Conta: ");
        int numeroConta = Integer.parseInt(s.nextLine());
        System.out.println("Digite o Digito: ");
        int digito = Integer.parseInt(s.nextLine());
        System.out.println("Digite a Senha: ");
        String senha = s.nextLine();
        if (banco.logar(agencia, numeroConta, digito, senha)) {
            this.contaLogada = contaCorrente;
            System.out.println("Login efetuado com sucesso.");
        } else {
            System.out.println("Dados inválidos. Tente novamente.");
        }
    }
}
