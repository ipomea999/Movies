package task3.util;

import task3.models.Movie;
import task3.models.MovieWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/task3.data/movies.json");

    public static List<Movie> readMovies() {
        try {
            String read = Files.readString(PATH);
            MovieWrapper wrapper = GSON.fromJson(read, MovieWrapper.class);
            return wrapper.getMovies();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}