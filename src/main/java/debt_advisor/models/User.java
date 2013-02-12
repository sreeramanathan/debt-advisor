package debt_advisor.models;

public class User {
    private String forename;
    private String surname;
    private boolean empty;
    private static final String EMPTY_USER_FORENAME = "Forename";
    private static final String EMPTY_USER_SURNAME = "Surname";
    public static final User EMPTY_USER = new User();

    public User(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
        this.empty = false;
    }

    public User() {
        this.forename = EMPTY_USER_FORENAME;
        this.surname = EMPTY_USER_SURNAME;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (forename != null ? !forename.equals(user.forename) : user.forename != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forename != null ? forename.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}