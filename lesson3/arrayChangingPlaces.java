//1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
package lesson_3;

import java.util.Scanner;

public class arrayChangingPlaces {
    public static void main(String[] args) {
        System.out.println("Задание №1");
        System.out.println();

        String[] tourismProgram = {"Посещение Сан-Марино.", "Карабельная площадь в Риме.", "Галерея Уффици во Флоренции.", "Площадь Чудес в Пизе.", "Сиена."};

        printArray(tourismProgram);
        tourismProgram = ChangingPlaces(tourismProgram);
        printArray(tourismProgram);
    }

    public static String[] ChangingPlaces(String[] arr) {
        int x1;
        int x2;
        System.out.print("\nКакие 2 экскурсии поменяем местами?");
        do {
            System.out.println("\nВведите два порядковых номера из списка:");
            Scanner scanner = new Scanner(System.in);
            x1 = scanner.nextInt() - 1;
            x2 = scanner.nextInt() - 1;
        } while ((x1 < 0 || x1 >= arr.length || x2 < 0 || x2 >= arr.length));
        String exchange = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = exchange;
        return arr;
    }

    public static void printArray(String[] arr) {
        System.out.println("Экскурсионная программа:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + 1 + ". " + arr[i]);
        }
    }
}
