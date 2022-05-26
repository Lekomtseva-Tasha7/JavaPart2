package lesson_4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        // Посчитать, сколько раз встречается каждое слово.

        String[] flowers = {"Роза", "Гербера", "Гипсофила", "Альстромерия", "Лилия",
                "Роза", "Пион", "Анемон", "Тюльпан", "Калл",
                "Орхидея", "Подсолнух", "Анемон", "Агапантус", "Стрелиция",
                "Плюмерия", "Гипсофила", "Гербера", "Хризантема", "Гипсофила"};
        System.out.println("Длинна массива = " + flowers.length + "\n" + Arrays.toString(flowers));

        LinkedHashSet<String> flowersHashSet = new LinkedHashSet (Arrays.asList(flowers));
        System.out.println("\nДлинна массива без повторений = " + flowersHashSet.size() + "\n" + flowersHashSet);

        System.out.println("\nСколько раз повторяется элемент массива:");

        for (String s : flowersHashSet) {
            int k = 0;
            for (String flower : flowers) {
                if (s.equals(flower)) {
                    k++;
                }
            }
            System.out.println(s + " - " + k);
        }
    }
}
