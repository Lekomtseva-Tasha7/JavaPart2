package lesson_1;

import java.text.DecimalFormat;

public class Team {
    private String teamName;
    private Animal[] animal = new Animal[4];
    private int x;

    public Team(String teamName) {
        this.teamName = teamName;
        animal[0] = new Cat("Барсик", 1, 2, 3, 10, 5, 120);
        animal[1] = new Cat("Мурзик", 3, 3, 3, 12, 10, 100);
        animal[2] = new Wolf("Серый", 2, 6, 2, 10, 50, 500);
        animal[3] = new Otter("Плавунец", 5, 20, 1, 5, 500, 20);
    }

    public String getTeamName() {
        return teamName;
    }

    public Animal[] getAnimal() {
        return animal;
    }

    public void showTeam() {
        System.out.println("\nКоманда " + teamName + ":");
        for (int i = 0; i < animal.length; i++) {
            System.out.println(i + 1 + " участник = " + animal[i].getType() + " " + animal[i].getName());
        }
        System.out.println();
    }

    public void showResults(Course courses) {
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("\nЧемпионы команды " + teamName + ":");
        for (int i = 0; i < animal.length; i++) {
            if (animal[i] != null) {
                System.out.println(animal[i] + " преодолел всю дистанцию за " + df.format(courses.getTime()[i] ) + " мин.");
                x++;
            }
        }
        if (x == 0) System.out.println("Победителей нет.");
    }
}

