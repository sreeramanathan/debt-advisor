package debt_advisor.models;

public class User {
    private String forename;
    private String surname;
    private boolean empty;

    public User(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
        this.empty = false;
    }

    public User() {
        this.forename = "Forename";
        this.surname = "Surname";
        this.empty = true;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isEmpty() {
        return empty;
    }
}