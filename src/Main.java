import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, ContaCorrente> contas = new HashMap<>();
        Banco banco = new Banco(contas);
        ATM atm = new ATM(banco);
        System.out.println("Isso é um teste: \n");
        Scanner s = new Scanner(System.in);
        System.out.println("Digite a opção:\n 1.Criar\n2.Logar");
        String opcao = s.nextLine();
        switch (opcao){
            case "1":
                atm.abrirConta();
                break;

            case "2":
                ContaCorrente contaCorrente = atm.logar();
                System.out.println("Digite a operacao:\n 1.Saldo\n2.Sacar");
                String operacao = s.nextLine();
                switch (operacao){
                    case "1":
                        atm.saldo();
                        break;
                    case "2":
                        atm.realizarSaque();
                        break;
                }
                break;

        }
    }
}