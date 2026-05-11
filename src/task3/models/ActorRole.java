package task3.models;

public class ActorRole {
    private String movieName;
    private String role;

    public ActorRole(String movieName, String role) {
        this.movieName = movieName;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Фильм: '" + movieName + "' | Роль: " + role;
    }
}
