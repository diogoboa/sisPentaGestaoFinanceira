package penta.sisPenta.gestaoFinanceira.Exceptions.Custom;

public class FalhaAoSerializarMensagem extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FalhaAoSerializarMensagem(String msg) {

        super(msg);

    }
}
