package penta.sisPenta.gestaoFinanceira.Exceptions.Custom;

public class RestInvalidArgumentException  extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public RestInvalidArgumentException(String msg) {

        super(msg);

    }

}
