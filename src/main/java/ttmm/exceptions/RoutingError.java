package ttmm.exceptions;


public class RoutingError extends RuntimeException{
    private int statusCode=409;

    public RoutingError(String message, int statusCode){
        super(message);
    }

    public RoutingError(String message, int statusCode, Throwable cause){
        super(message, cause);
    }

    public RoutingError(int statusCode){
        this.statusCode=statusCode;
    }

    public RoutingError(String message){
        super(message);
    }

}
