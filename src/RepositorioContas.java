import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RepositorioContas {
    private HashMap<String, ContaCorrente> contas;

    private int agencia = 1;
    private int digito = new Random().nextInt(10);
    private final String theKeeper = "contas.json";


    public RepositorioContas(HashMap<String, ContaCorrente> contas) {
        this.contas = contas;
        loadContas();
    }
    private void loadContas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(theKeeper))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ContaCorrente>() {}.getType();
                ContaCorrente conta = gson.fromJson(line, type);
                String key = conta.getAgencia() + "/" + conta.getNumeroConta() + "-" + conta.getDigito();
                contas.put(key, conta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ContaCorrente getConta(int agencia, int numeroConta, int digito) {
        String key = agencia + "/" + numeroConta + "-" + digito;
        return contas.get(key);
    }

    public void addConta(ContaCorrente conta) {
        String key = conta.getAgencia() + "/" + conta.getNumeroConta() + "-" + conta.getDigito();
        contas.put(key, conta);
        System.out.println("Conta Adicionada: " + key);
    }

    public void abrirConta(String nome, String documento, String telefone, String email, String senha) {
        Pessoa cliente = new Pessoa(nome, documento, telefone, email);
        int numeroConta = getUltimaContaCriada() + 1;
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
    public void atualizaLinhaJson(ContaCorrente conta) {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader(theKeeper))) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                ContaCorrente c = gson.fromJson(line, ContaCorrente.class);
                if (c.getAgencia() == conta.getAgencia()
                        && c.getNumeroConta() == conta.getNumeroConta()
                        && c.getDigito() == conta.getDigito()) {
                    line = gson.toJson(conta);
                }
                fileContent.append(line).append("\n");
            }
            try (FileWriter writer = new FileWriter(theKeeper)) {
                writer.write(fileContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}