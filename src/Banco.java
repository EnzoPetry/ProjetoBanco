import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class Banco {
    private Map<String, ContaCorrente> contas;
    private int agencia = 1;
    private int numeroConta = 0;
    private int digito = new Random().nextInt(10);
    private final String theKeeper = "contas.json";



    public Banco(Map<String, ContaCorrente> contas) {
        this.contas = contas;
    }
    public ContaCorrente getConta(int agencia, int numero, int digito) {
        String key = agencia + "/" + numero  + "-" + digito;
        return contas.get(key);
    }
    public void addConta(ContaCorrente conta) {
        String key = conta.getAgencia() + "/" + conta.getNumeroConta() + "-" + conta.getDigito();
        contas.put(key, conta);
    }

    public void abrirConta(String nome, String documento, String telefone, String email,String senha) {
        Pessoa cliente = new Pessoa(nome,documento,telefone,email);
        this.numeroConta++;
        ContaCorrente conta = new ContaCorrente(agencia, numeroConta, digito, cliente, senha);
        addConta(conta);
        Gson gson = new Gson();
        String json = gson.toJson(conta);

        try (FileWriter writer = new FileWriter(theKeeper, true)) {
            writer.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
