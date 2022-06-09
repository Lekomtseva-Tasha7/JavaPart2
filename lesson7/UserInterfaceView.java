package lesson_7;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private static Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command;
            do {
                System.out.println("Для получения прогноза погоды или выхода введите:\n1 - прогноз погоды на сегодня;" +
                        "\n5 - прогноз погоды на 5 дней;" +
                        "\n0 - ВЫХОД:");
                command = scanner.nextLine();
            } while (!(command.equals("5") || command.equals("1") || command.equals("0")));

            if (command.equals("0")) break;

            System.out.println("Введите название города: ");
            String city = scanner.nextLine();

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                System.out.println("При получении погоды произошла ошибка. Попробуйте позже.");
            }
        }
    }

    public static void main(String[] args) {
        UserInterfaceView view = new UserInterfaceView();
        view.runInterface();
    }
}
