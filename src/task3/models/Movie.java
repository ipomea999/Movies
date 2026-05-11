package task3.models;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private String description;
    private Director director;
    private List<Actor> cast;

    public String getName() {
        return name;
    }
    public int getYear() {
        return year;
    }
    public String getDescription() {
        return description;
    }
    public Director getDirector() {
        return director;
    }
    public List<Actor> getCast() {
        return cast;
    }

    @Override
    public String toString() {
        return String.format("Фильм: '%s' | Год: %d | Режиссер: %s",
                name, year, director != null ? director.getFullName() : "Неизвестен");
    }
}