import models.Movie;
import service.MovieService;
import service.MovieMenu;
import util.FileUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Movie> loadedMovies = FileUtil.readMovies();

        MovieService movieService = new MovieMenu(loadedMovies);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Меню коллекции фильмов ===");
            System.out.println("1. Вывести все фильмы");
            System.out.println("2. Найти фильм по названию");
            System.out.println("3. Сортировка по году возрастание");
            System.out.println("4. Сортировка по году убывание");
            System.out.println("5. Сортировка по название возрастание");
            System.out.println("6. Сортировка по название убывание");
            System.out.println("7. Сортировка по режиссеру возрастание");
            System.out.println("8. Сортировка по режиссеру убывание");
            System.out.println("0. Выход");
            System.out.print("Введите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    movieService.printAllMovies();
                    break;
                case 2:
                    System.out.print("Введите название для поиска: ");
                    String nameMovie = scanner.nextLine();
                    List<Movie> found = movieService.searchByName(nameMovie);
                    movieService.printMovies(found);
                    break;
                case 3:
                    movieService.printMovies(movieService.sortByYear(true));
                    break;
                case 4:
                    movieService.printMovies(movieService.sortByYear(false));
                    break;
                case 5:
                    movieService.printMovies(movieService.sortByName(true));
                    break;
                case 6:
                    movieService.printMovies(movieService.sortByName(false));
                    break;
                case 7:
                    movieService.printMovies(movieService.sortByDirector(true));
                    break;
                case 8:
                    movieService.printMovies(movieService.sortByDirector(false));
                    break;
                case 0:
                    System.out.println("Завершение работы программы");
                    return;
                default:
                    System.out.println("Ошибка: неверный выбор!");
            }
        }
    }
}