package service;

import models.*;

import java.util.*;

public class MovieMenu implements MovieService {
    private final List<Movie> movies;

    private final Map<String, List<Movie>> actorIndex = new HashMap<>();
    private final Map<String, List<Movie>> directorIndex = new HashMap<>();
    private final Map<Integer, List<Movie>> yearIndex = new HashMap<>();

    public MovieMenu(List<Movie> movies) {
        this.movies = movies;
        buildIndexes();
    }

    private void buildIndexes() {
        for (Movie movie : movies) {
            yearIndex.computeIfAbsent(movie.getYear(), k -> new ArrayList<>()).add(movie);

            if (movie.getDirector() != null) {
                String dirKey = movie.getDirector().getFullName().toLowerCase();
                directorIndex.computeIfAbsent(dirKey, k -> new ArrayList<>()).add(movie);
            }

            if (movie.getCast() != null) {
                for (Actor actor : movie.getCast()) {
                    String actorKey = actor.getFullName().toLowerCase();
                    actorIndex.computeIfAbsent(actorKey, k -> new ArrayList<>()).add(movie);
                }
            }
        }
    }

    @Override
    public void printMovies(List<Movie> moviesToPrint) {
        if (moviesToPrint == null || moviesToPrint.isEmpty()) {
            System.out.println("Ничего не найдено.");
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
    public List<Movie> getMoviesByActor(String actorName) {
        List<Movie> result = new ArrayList<>();
        String query = actorName.toLowerCase();

        for (Map.Entry<String, List<Movie>> entry : actorIndex.entrySet()) {
            if (entry.getKey().contains(query)) {
                for (Movie m : entry.getValue()) {
                    if (!result.contains(m)) result.add(m);
                }
            }
        }
        return result;
    }

    @Override
    public List<Movie> getMoviesByDirector(String directorName) {
        List<Movie> result = new ArrayList<>();
        String query = directorName.toLowerCase();

        for (Map.Entry<String, List<Movie>> entry : directorIndex.entrySet()) {
            if (entry.getKey().contains(query)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        return new ArrayList<>(yearIndex.getOrDefault(year, new ArrayList<>()));
    }

    @Override
    public Map<Movie, String> getMoviesAndRolesByActor(String actorName) {
        Map<Movie, String> result = new LinkedHashMap<>();
        List<Movie> actorMovies = getMoviesByActor(actorName);
        String query = actorName.toLowerCase();

        for (Movie m : actorMovies) {
            for (Actor a : m.getCast()) {
                if (a.getFullName().toLowerCase().contains(query)) {
                    result.put(m, a.getRole());
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<String>> getAllActorsWithRoles() {
        Map<String, Set<String>> allActors = new TreeMap<>();

        for (Movie movie : movies) {
            if (movie.getCast() != null) {
                for (Actor actor : movie.getCast()) {
                    allActors.computeIfAbsent(actor.getFullName(), k -> new HashSet<>()).add(actor.getRole());
                }
            }
        }
        return allActors;
    }

    @Override
    public List<Movie> sortMovies(List<Movie> moviesToSort, int sortBy, boolean ascending) {
        List<Movie> sortedList = new ArrayList<>(moviesToSort);

        Comparator<Movie> comparator = switch (sortBy) {
            case 1 -> Comparator.comparingInt(Movie::getYear);
            case 2 -> Comparator.comparing(Movie::getName, String.CASE_INSENSITIVE_ORDER);
            case 3 -> (m1, m2) -> {
                String d1 = m1.getDirector() != null ? m1.getDirector().getFullName() : "";
                String d2 = m2.getDirector() != null ? m2.getDirector().getFullName() : "";
                return String.CASE_INSENSITIVE_ORDER.compare(d1, d2);
            };
            default -> null;
        };

        if (comparator != null) {
            if (!ascending) comparator = comparator.reversed();
            sortedList.sort(comparator);
        }
        return sortedList;
    }
}