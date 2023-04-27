import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ControladorBanco {
    private ATM atm;
    private final RepositorioContas repositorio;

    private ControladorBanco(ATM atm) {
        this.atm = atm;
        this.repositorio = new RepositorioContas(new HashMap<>());

    }

    public static void iniciar() {
        Path contas = Paths.get("contas.json");

        if (!Files.exists(contas)) {
            try {
                Files.createFile(contas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RepositorioContas repositorio = new RepositorioContas(new HashMap<>());
        OperacaoBanco operacao = new OperacaoBanco(repositorio);
        ATM atm = new ATM(operacao);
        ControladorBanco controladorBanco = new ControladorBanco(atm);

        controladorBanco.carregarContas();
        controladorBanco.setupInicial();
    }

    private void setupInicial() {
        atm.telaBoasVindas();
    }

    private void carregarContas() {
        repositorio.carregarContas();
    }
}
