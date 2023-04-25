public class ControladorBanco {
    private ATM atm;

    public ControladorBanco(ATM atm) {
        this.atm = atm;
    }

    public void setupInicial(){
        atm.telaBoasVindas();
    }

}
