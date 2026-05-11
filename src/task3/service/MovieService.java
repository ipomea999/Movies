package task3.service;

import task3.models.Movie;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MovieService {
    void printMovies(List<Movie> movies);
    void printAllMovies();
    List<Movie> searchByName(String query);

    List<Movie> getMoviesByActor(String actorName);
    List<Movie> getMoviesByDirector(String directorName);
    List<Movie> getMoviesByYear(int year);

    Map<Movie, String> getMoviesAndRolesByActor(String actorName);

    Map<String, Set<String>> getAllActorsWithRoles();

    List<Movie> sortMovies(List<Movie> moviesToSort, int sortBy, boolean ascending);
}