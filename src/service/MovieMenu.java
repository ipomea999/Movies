package service;
import models.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieMenu implements MovieService {
    private final List<Movie> movies;

    public MovieMenu(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void printMovies(List<Movie> moviesToPrint) {
        if (moviesToPrint.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        for (Movie movie : moviesToPrint) {
            System.out.println(movie);
        }
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void printAllMovies() {
        printMovies(this.movies);
    }

    @Override
    public List<Movie> searchByName(String query) {
        List<Movie> result = new ArrayList<>();
        String queryLower = query.toLowerCase();

        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(queryLower)) {
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public List<Movie> sortByYear(boolean ascending) {
        List<Movie> sortedList = new ArrayList<>(movies);

        Comparator<Movie> byYear = new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Integer.compare(m1.getYear(), m2.getYear());
            }
        };

        if (ascending) {
            sortedList.sort(byYear);
        } else {
            sortedList.sort(byYear.reversed());
        }
        return sortedList;
    }

    @Override
    public List<Movie> sortByName(boolean ascending) {
        List<Movie> sortedList = new ArrayList<>(movies);

        Comparator<Movie> byName = new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getName().compareToIgnoreCase(m2.getName());
            }
        };

        if (ascending) {
            sortedList.sort(byName);
        } else {
            sortedList.sort(byName.reversed());
        }
        return sortedList;
    }

    @Override
    public List<Movie> sortByDirector(boolean ascending) {
        List<Movie> sortedList = new ArrayList<>(movies);

        Comparator<Movie> byDirector = new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                String dir1 = m1.getDirector().getFullName();
                String dir2 = m2.getDirector().getFullName();
                return dir1.compareToIgnoreCase(dir2);
            }
        };

        if (ascending) {
            sortedList.sort(byDirector);
        } else {
            sortedList.sort(byDirector.reversed());
        }
        return sortedList;
    }
}