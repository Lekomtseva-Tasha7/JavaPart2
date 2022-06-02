package lesson_5;

import java.io.*;

public class Files {
    public static void main(String[] args) throws IOException {
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = {{100, 200, 123},
                {300, 400, 500}};
        AppData appData = new AppData(header, data);

        //1. Реализация сохранения данных в csv файл;
        try (PrintWriter printWriter = new PrintWriter("output.csv")) {
            for (String str : appData.getHeader()) {
                printWriter.print(str + ";");
            }
            printWriter.println();
            for (int[] data1 : appData.getData()) {
                for (int data2 : data1) {
                    printWriter.print(data2 + ";");
                }
                printWriter.println();
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        //2. Реализация загрузки данных из csv файла. Файл читается целиком.
        AppData appData1 = new AppData(new String[header.length], new int[data.length][data[0].length]);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("output.csv"))) {
            String tempHeader;
            String [][] temp = new String[3][3];
            for (int i = 0; i < 3; i++) {
                tempHeader = bufferedReader.readLine();
                temp[i] = tempHeader.split(";");
            }
            appData1.setHeader(temp[0]);
            appData1.setData(new int[][] {{Integer.parseInt(temp[1][0]), Integer.parseInt(temp[1][1]), Integer.parseInt(temp[1][2])},
                    {Integer.parseInt(temp[2][0]), Integer.parseInt(temp[2][1]), Integer.parseInt(temp[2][2])}});
                   } catch (IOException e) {
                e.printStackTrace();
            }

        System.out.println("appData\n" + appData);
        System.out.println();
        System.out.println("appData1\n" + appData1);
        System.out.println();

        String[] header3 = {"*1*", "*2*", "*3*"};
        int[][] data3 = {{1, 2, 3}, {4, 5, 6}};
        AppData appData3 = new AppData(header3, data3);

        System.out.println("appData3\n" + appData3);
        System.out.println();

        appData3.save(appData);
        System.out.println("Новые данные в appData3\n" + appData3);
    }
}
