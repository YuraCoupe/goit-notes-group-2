package ua.goit.group2notes.note;

public enum NoteAccessType {
    PRIVATE("PRIVATE"),
    PUBLIC("PUBLIC"),
    ;

    private String type;

    NoteAccessType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
