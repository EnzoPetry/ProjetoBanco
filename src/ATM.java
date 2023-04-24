import java.util.Scanner;

public class ATM {

    private Banco banco;

    public ATM(Banco banco) {
        this.banco = banco;
    }

    public void abrirConta() {
        System.out.println("Abrindo conta...");
        System.out.println("Digite seu Nome:");
        Scanner s = new Scanner(System.in);
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
}
