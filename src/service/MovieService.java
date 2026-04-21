package service;

import models.Movie;
import java.util.List;

public interface MovieService {
    void printMovies(List<Movie> movies);
    void printAllMovies();
    List<Movie> searchByName(String query);
    List<Movie> sortByYear(boolean ascending);
    List<Movie> sortByName(boolean ascending);
    List<Movie> sortByDirector(boolean ascending);
}