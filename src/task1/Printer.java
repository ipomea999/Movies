package task1;

import java.util.List;

// код можно менять только в особо отмеченном месте этого файла,
// так же возможно вам понадобится добавить что-то в 
// блок import и поменять имя пакета
// в остальных местах этого файла код менять не разрешается.

public final class Printer {
    private static final void printHeader() {
        System.out.println(String.format("%1$10.10s | %1$3.3s | %1$-10.10s | %1$-10.10s |", "--------------"));
        System.out.println(String.format("%10.10s | %3.3s | %-10.10s | %-10.10s |", "Name", "Age", "Breed", "Color"));
        System.out.println(String.format("%1$10.10s | %1$3.3s | %1$-10.10s | %1$-10.10s |", "--------------"));
    }

    private static final void printCat(Cat cat) {
        System.out.println(String.format("%10.10s | %3.3s | %-10.10s | %-10.10s |", cat.getName(), cat.getAge(),
                cat.getBreed(), cat.getColor()));
    }

    public static final void print(List<Cat> cats) {
        printHeader();
        cats.forEach(Printer::printCat);
        System.out.println();
    }
}
