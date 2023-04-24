import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, ContaCorrente> contas = new HashMap<>();
        Banco banco = new Banco(contas);
        ATM atm = new ATM(banco);

        atm.abrirConta();
        System.out.println("Isso é um teste: \n");
        atm.abrirConta();
    }
}