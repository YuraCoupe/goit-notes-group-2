package ua.goit.errorHandling;

public class TitleNotFoundException extends RuntimeException{
    public TitleNotFoundException(String message) {
        super(message);
    }

}
