public class Pessoa {
    private String nome;
    private String telefone;
    private String email;
    private String tipoConta;
    private String documento;

    public Pessoa(String tipoConta,String nome,String documento, String telefone, String email) {
        this.tipoConta = tipoConta;
        this.documento = documento;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
