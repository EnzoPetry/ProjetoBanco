import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Random;

public class Banco {
    private Map<String, ContaCorrente> contas;
    private int agencia = 1;
    private int numeroConta;
    private int digito = new Random().nextInt(10);
    private final String theKeeper = "contas.json";


    public Banco(Map<String, ContaCorrente> contas) {
        this.contas = contas;
        this.numeroConta = getUltimaContaCriada();
    }

    private int getUltimaContaCriada() {
        int numeroConta = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("contas.json"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ContaCorrente>() {
                }.getType();
                ContaCorrente conta = gson.fromJson(line, type);
                if (conta.getNumeroConta() > numeroConta) {
                    numeroConta = conta.getNumeroConta();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numeroConta;
    }


    public ContaCorrente getConta(int agencia, int numero, int digito) {
        String key = agencia + "/" + numero + "-" + digito;
        return contas.get(key);
    }

    public void addConta(ContaCorrente conta) {
        String key = conta.getAgencia() + "/" + conta.getNumeroConta() + "-" + conta.getDigito();
        contas.put(key, conta);
    }

    public void abrirConta(String nome, String documento, String telefone, String email, String senha) {
        Pessoa cliente = new Pessoa(nome, documento, telefone, email);
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
