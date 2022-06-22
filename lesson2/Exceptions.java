package lesson_2;

//1. Метод, на вход которого подаётся двумерный строковый массив размером 4х4.
// При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст
// вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
// в какой именно ячейке лежат неверные данные.
//3. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException
// и MyArrayDataException и вывести результат расчета.

import java.util.Random;
import java.util.Arrays;

public class Exceptions {

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
                                    {"5", "6", "7", "8"},
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

    public static boolean possibility(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static int sumArray (String[][] strings) throws MyArraySizeException, MyArrayDataException{
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                if (strings.length != 4 || strings[i].length != 4)
                    throw new MyArraySizeException("Массив неверного размера (должен быть 4х4).");
                if (!possibility(strings[i][j]))
                    throw new MyArrayDataException("Данные в ячейке[" + (i + 1) + "][" + (j + 1) + "]='" + strings[i][j] + "' невозможно преобразовать в число.");
                    sum += Integer.parseInt(strings[i][j]);
            }
        }
        return sum;
    }
}