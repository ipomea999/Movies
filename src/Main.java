import models.Movie;
import service.MovieMenu;
import service.MovieService;
import util.FileUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static MovieService movieService;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n=== МЕНЮ ФИЛЬМОВ ===");
            System.out.println("1. Считать файл заново");
            System.out.println("2. Вывести все фильмы");
            System.out.println("3. Поиск по названию фильма");
            System.out.println("4. Поиск фильмов по актёру");
            System.out.println("5. Поиск фильмов по режиссёру");
            System.out.println("6. Поиск фильмов по году");
            System.out.println("7. Фильмы и Роли по актёру");
            System.out.println("8. Все Актеры и их Роли сортировка по алфавиту");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    loadData();
                }
                case 2 -> {
                    processSelection(movieService.searchByName(""));
                }
                case 3 -> {
                    System.out.print("Введите название фильма: ");
                    processSelection(movieService.searchByName(scanner.nextLine()));
                }
                case 4 -> {
                    System.out.print("Введите имя актера: ");
                    processSelection(movieService.getMoviesByActor(scanner.nextLine()));
                }
                case 5 -> {
                    System.out.print("Введите имя режиссера: ");
                    processSelection(movieService.getMoviesByDirector(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Введите год: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    processSelection(movieService.getMoviesByYear(year));
                }
                case 7 -> {
                    System.out.print("Введите имя актера: ");
                    Map<Movie, String> moviesAndRoles = movieService.getMoviesAndRolesByActor(scanner.nextLine());
                    if (moviesAndRoles.isEmpty()) System.out.println("Ничего не найдено");
                    for (Map.Entry<Movie, String> entry : moviesAndRoles.entrySet()) {
                        System.out.println(entry.getKey().getName() + " — роль: " + entry.getValue());
                    }
                }
                case 8 -> {
                    Map<String, Set<String>> allActors = movieService.getAllActorsWithRoles();
                    for (Map.Entry<String, Set<String>> entry : allActors.entrySet()) {
                        System.out.println("Актер: " + entry.getKey() + " | Роли: " + String.join(", ", entry.getValue()));
                    }
                }
                case 0 -> {
                    System.out.println("Программа завершена");
                    return;
                }
                default -> System.out.println("Ошибка: неверная команда!");
            }
        }
    }

    private static void loadData() {
        System.out.println("Загрузка данных из файла");
        List<Movie> loadedMovies = FileUtil.readMovies();
        movieService = new MovieMenu(loadedMovies);
        System.out.println("Успешная загрузка");
    }

    private static void processSelection(List<Movie> selection) {
        if (selection.isEmpty()) {
            System.out.println("Ничего не найдено");
            return;
        }

        movieService.printMovies(selection);

        System.out.println("Хотите отсортировать этот результат? ( y/n)");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("y")) {
            System.out.println("1 - по году, 2 - по названию, 3 - по режиссеру");
            int sortBy = Integer.parseInt(scanner.nextLine());

            System.out.println("По возрастанию? (y/n)");
            String ascAns = scanner.nextLine().toLowerCase();
            boolean asc = ascAns.equals("y");

            List<Movie> sortedSelection = movieService.sortMovies(selection, sortBy, asc);
            System.out.println("\n--- Отсортированный результат ---");
            movieService.printMovies(sortedSelection);
        }
    }
}