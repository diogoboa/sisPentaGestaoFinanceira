package penta.sisPenta.gestaoFinanceira.Exceptions.Custom;

public class PageNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public PageNotFoundException(String msg) {

        super(msg);

    }

}
