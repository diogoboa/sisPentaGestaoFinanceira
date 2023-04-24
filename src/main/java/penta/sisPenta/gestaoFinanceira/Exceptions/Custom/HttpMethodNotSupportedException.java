package penta.sisPenta.gestaoFinanceira.Exceptions.Custom;

public class HttpMethodNotSupportedException extends RuntimeException{


    //HttpRequestMethodNotSupportedException

    private static final long serialVersionUID = 1L;

    public HttpMethodNotSupportedException(String msg) {

        super(msg);

    }

}
