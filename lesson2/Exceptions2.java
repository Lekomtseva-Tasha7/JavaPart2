package lesson_2;

import java.util.Arrays;
import java.util.Random;

public class Exceptions2 {
    public static void main(String[] args) {

        String[][] strings = {{"1", "2", "3", "4"},
                             {"5", "6", "7", "8"},
                             {"9", "-10", "11", "12"},
                             {"13", "14", "15", "16"}};

        String[][] stringsWrongSize = {{"1", "2", "3", "4"},
                                       {"5", "6", "7"},
                                       {"9", "10"}};

        String[][] stringsWrongSize1 = {{"1", "2", "3"},
                                        {"5", "6", "7"},
                                        {"9", "10", "11"},
                                        {"13", "14", "15"}};


        String[][] stringsMistake = {{"1", "2", "3", "4"},
                                     {"5", "-", "7", "8"},
                                     {"9", "десять", "11", "12"},
                                     {"13", "14", "%", "16"}};

        String[][][] variable = {strings, stringsWrongSize, stringsWrongSize1, stringsMistake, };
        Random random = new Random();
        int x = random.nextInt(4);

        String[][] superArray = variable[x];

        System.out.println("Наш случайный строковый массив:");
        for (String[] str : superArray) {
            System.out.println(Arrays.deepToString(str));
        }

        try {
            System.out.println("\nВсе прошло успешно.\nСумма элементов массива = " + sumArray(superArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int sumArray (String[][] strings) throws MyArraySizeException, MyArrayDataException{
        int sum = 0;
        int q = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                if (strings.length != 4 || strings[i].length != 4)
                    throw new MyArraySizeException("Массив неверного размера (должен быть 4х4).");
                try {sum += Integer.parseInt(strings[i][j]);
                    } catch (NumberFormatException e) {
                        q++;
                        System.err.println("Данные в ячейке[" + (i + 1) + "][" + (j + 1) + "]='" + strings[i][j] + "' невозможно преобразовать в число.");
                    }
                }
            }
        if (q != 0) throw new MyArrayDataException("В массиве неверные данный!");
        return sum;
    }
}
