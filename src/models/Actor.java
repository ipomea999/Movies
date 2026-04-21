package models;

public class Actor {
    private String fullName;
    private String role;

    public String getFullName() {
        return fullName;
    }
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return fullName + " (" + role + ")";
    }
}