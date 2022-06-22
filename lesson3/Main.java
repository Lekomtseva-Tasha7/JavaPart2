/*2.Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
Не забываем про метод добавления фрукта в коробку.*/
package lesson_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание №2");

        ArrayList<Apple> appleArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            appleArrayList.add(new Apple());
        }
        Box<Apple> boxApple = new Box<>(appleArrayList);

        ArrayList<Apple> appleArrayList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            appleArrayList2.add(new Apple());
        }
        Box<Apple> boxApple2 = new Box<>(appleArrayList2);

        ArrayList<Orange> orangeArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            orangeArrayList.add(new Orange());
        }
        Box<Orange> boxOrange = new Box<>(orangeArrayList);

        System.out.println("\nМы везем 3 коробки с фруктами:");
        boxApple.printBox(boxOrange, boxApple2);

        int x;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\nКакую коробку пополним фруктами? (введите число от 1 до 3)");
            do {
                x = scanner.nextInt();
            } while (x < 1 || x > 3);
            if (x == 1) {
                System.out.println("Сколько яблок добавим в коробку №1?");
                boxApple.addingFruits();
            } else if (x == 2) {
                System.out.println("Сколько апельсин добавим в коробку №2?");
                boxOrange.addingFruits();
            } else {
                System.out.println("Сколько яблок добавим в коробку №3?");
                boxApple2.addingFruits();
            }
            do {
                System.out.println("Повторим? (Да - 1, Нет - 2)");
                x = scanner.nextInt();
            } while (x < 1 || x > 2);
        } while (x == 1);

        System.out.println("\nТеперь веса коробок изменились:");
        boxApple.printBox(boxOrange, boxApple2);

        do {
            System.out.println("\nВес какой коробки сравним с весом коробки №1? (введите число 2 или 3)");
            do {
                x = scanner.nextInt();
            } while (x < 2 || x > 3);
            if (x == 2) {
            if (boxApple.compare(boxOrange)) {
                System.out.println("Коробки одинакового веса.");
            } else System.out.println("Коробки разного веса.");
            } else {
                if (boxApple.compare(boxApple2)) {
                    System.out.println("Коробки одинакового веса.");
                } else System.out.println("Коробки разного веса.");
            }
            do {
                System.out.println("Повторим? (Да - 1, Нет - 2)");
                x = scanner.nextInt();
            } while (x < 1 || x > 2);
        } while (x == 1);

        do {
            System.out.println("\nИз какой коробки пересыпем фрукты в коробку №1? (введите число 2 или 3)");
            do {
                x = scanner.nextInt();
            } while (x < 2 || x > 3);
            if (x == 2) {
                System.out.println("\nПересыпаем апельсины из коробки №2 в коробку №1");
                System.out.println("К сожалению, апельсины и яблоки нельзя хранить вместе.");
            } else {
                System.out.println("\nПересыпаем яблоки из коробки №3 в коробку №1");
                boxApple.removeFruits(boxApple2);
                System.out.println("\nТеперь веса коробок изменились:");
                boxApple.printBox(boxOrange, boxApple2);
            }
            do {
                System.out.println("Повторим? (Да - 1, Нет - 2)");
                x = scanner.nextInt();
            } while (x < 1 || x > 2);
        } while (x == 1);
    }
}