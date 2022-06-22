package ua.goit.group2notes.errorHandling;

public class TitleAlreadyExistsException extends RuntimeException {
    public TitleAlreadyExistsException(String message) {
        super(message);
    }
}
