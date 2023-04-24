package penta.sisPenta.gestaoFinanceira.Exceptions.Custom;

public class UnprocessableEntity extends RuntimeException{


    //HttpRequestMethodNotSupportedException

    private static final long serialVersionUID = 1L;

    public UnprocessableEntity(String msg) {

        super(msg);

    }

}
