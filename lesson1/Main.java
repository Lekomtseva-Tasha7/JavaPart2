package lesson_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите название команды:");
        Scanner scanner = new Scanner(System.in);
        String teamName = scanner.nextLine();

        Team team1 = new Team(teamName);

        team1.showTeam();

        System.out.println("Введите параметры полосы препятствий в метрах \nдлина бассейна:");
        double sizePool = scanner.nextDouble();
        System.out.println("высота забора:");
        double sizeFence = scanner.nextDouble();
        System.out.println("дальность забега:");
        double sizeTrail = scanner.nextDouble();

        Course courses = new Course(sizePool, sizeFence, sizeTrail);

        courses.showCourse();

        courses.doIt(team1);

        team1.showResults(courses);
    }
}
