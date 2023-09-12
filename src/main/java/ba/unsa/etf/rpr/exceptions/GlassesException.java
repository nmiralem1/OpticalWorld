package ba.unsa.etf.rpr.exceptions;

public class GlassesException extends Exception {

    public GlassesException(String message, Exception e) {
        super(message,e);
    }
    public GlassesException(String message){
        super(message);
    }
}