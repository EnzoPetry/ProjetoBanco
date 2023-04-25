import java.util.HashMap;

public class ControladorBanco {
    private ATM atm;

    public ControladorBanco(ATM atm) {
        this.atm = atm;
    }
    public static void iniciar(){
        RepositorioContas repositorio = new RepositorioContas(new HashMap<>());
        OperacaoBanco operacao = new OperacaoBanco(repositorio);
        ATM atm = new ATM(operacao);
        ControladorBanco controladorBanco = new ControladorBanco(atm);
        controladorBanco.setupInicial();
    }
    public void setupInicial(){
        atm.telaBoasVindas();
    }


}
