public class Pessoa {
    private String nome;
    private String documento;
    private String telefone;
    private String email;

    //Construtor de Pessoa
    public Pessoa(String nome, String documento, String telefone, String email) {
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
    }

   //Getters e Setters
        public String getNome () {
        return nome;
    }

        public String getDocumento () {
        return documento;
    }

        public String getTelefone () {
        return telefone;
    }

        public String getEmail () {
        return email;
    }

}
