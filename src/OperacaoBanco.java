import java.util.Scanner;

public class OperacaoBanco {

    private RepositorioContas repositorio;
    private ContaCorrente contaLogada;

    public OperacaoBanco(RepositorioContas repositorio) {
        this.repositorio = repositorio;
    }

    public void abrirConta() {
        Scanner s = new Scanner(System.in);

        System.out.println("\nVocê está abrindo uma nova conta com a Bradesco!\n");
        System.out.println("Você quer abrir uma Conta Física ou Jurídica:");
        String tipoConta = s.nextLine().toUpperCase();
        String nome = "";
        String documento = "";
        String telefone = "";
        String email = "";
        if (tipoConta.equals("PF")) {
            System.out.println("Informe seu Nome completo para continuar:");
            nome = s.nextLine();
            System.out.println("Informe seu documento CPF:");
            documento = s.nextLine();
            System.out.println("Informe seu numero de telefone:");
            telefone = s.nextLine();
            System.out.println("Além disso precisamos de seu e-mail para deixa-lo sempre ciente das novidades:");
            email = s.nextLine();
        } else if (tipoConta.equals("PJ")) {
            System.out.println("Informe o nome da Empresa:");
            nome = s.nextLine();
            System.out.println("Informe seu documento CNPJ:");
            documento = s.nextLine();
            System.out.println("Informe o telefone empresarial:");
            telefone = s.nextLine();
            System.out.println("Insira o E-mail Administrativo:");
            email = s.nextLine();
        }
        boolean confirmacao = false;
        String senha = "";
        System.out.println("Digite a Senha para sua nova Conta:");
        while (!confirmacao) {
            senha = s.nextLine();
            System.out.println("Digite novamente sua Senha:");
            String senha2 = s.nextLine();
            if (senha.equals(senha2)) {
                confirmacao = true;
            } else {
                System.out.println(senha + " | " + senha2);
                System.out.println("Senhas não são iguais, insira novamente: ");
            }
        }
        repositorio.abrirConta(tipoConta, nome, documento, telefone, email, senha);

    }

    public boolean login(int agencia, int numeroConta, int digito, String senha) {
        ContaCorrente conta = repositorio.getConta(agencia, numeroConta, digito);
        if (conta != null && conta.getSenha().equals(senha)) {
            contaLogada = conta;
            System.out.println("Logado\n");
            return true;
        } else {
            System.out.println("Não Logado\n");
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (contaLogada.sacar(valor)) {
            repositorio.atualizaLinhaJson(contaLogada);
            return true;
        }
        return false;
    }

    public boolean depositar(double valor) {
        if (contaLogada.depositar(valor)) {
            repositorio.atualizaLinhaJson(contaLogada);
            return true;
        }
        return false;

    }

    public boolean transferir(int agenciaDestino, int contaDestino, int digitoDestino, double valor) {
        ContaCorrente contaOrigem = contaLogada;
        ContaCorrente contaOperacao = repositorio.getConta(agenciaDestino, contaDestino, digitoDestino);
        if (contaOperacao == null) {
            System.out.println("Conta de destino não encontrada");
            return false;
        }
        if (contaOrigem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        if (contaOrigem.sacar(valor) && contaOperacao.depositar(valor)) {
            System.out.println("Valor Adicionado na conta de " + contaLogada.getTitular().getNome());
            repositorio.atualizaLinhaJson(contaLogada);
            repositorio.atualizaLinhaJson(contaOperacao);
            return true;
        } else {
            System.out.println("Erro na Transacao");
            return false;

        }
    }

    ContaCorrente contaCorrente() {
        return contaLogada;
    }

}

