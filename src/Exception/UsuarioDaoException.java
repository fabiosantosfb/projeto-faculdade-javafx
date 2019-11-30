package Exception;

public class UsuarioDaoException extends Exception {
    private String msg;

    public UsuarioDaoException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}
