import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RepositorioContas repositorio = new RepositorioContas(new HashMap<String, ContaCorrente>());
        OperacaoBanco operacao = new OperacaoBanco(repositorio);
        ATM atm = new ATM(operacao);
        ControladorBanco controladorBanco = new ControladorBanco(atm);
        controladorBanco.setupInicial();
    }
}