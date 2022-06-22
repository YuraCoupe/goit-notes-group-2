package ua.goit.group2notes.errorHandling;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String message) {
        super(message);
    }
}
