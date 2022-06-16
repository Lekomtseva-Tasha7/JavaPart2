package lesson_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "QA"));
        courseList.add(new Course(2, "JAVA"));
        courseList.add(new Course(3, "PYTHON"));
        courseList.add(new Course(4, "WEB DESIGNER"));
        courseList.add(new Course(5, "SQLite"));
        courseList.add(new Course(6, "GIT"));
        courseList.add(new Course(7, "MANAGEMENT"));

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Евгений", Arrays.asList(courseList.get(0), courseList.get(1))));
        studentList.add(new Student("Елена", Arrays.asList(courseList.get(3))));
        studentList.add(new Student("Наталья", Arrays.asList(courseList.get(0), courseList.get(1), courseList.get(4), courseList.get(5))));
        studentList.add(new Student("Михаил", Arrays.asList(courseList.get(2), courseList.get(6))));


        //1. Функция принимает на вход список Student и возвращает список уникальных курсов, на которые подписаны студенты.
        System.out.println("1. Список уникальных курсов:\n" +
                studentList.stream()
                        .map(s -> s.getCourses())
                        .flatMap(c -> c.stream())
                        .map(s -> s.getName())
                        .collect(Collectors.toSet())
        );
        
        //2. Функция принимает на вход список Student и возвращающую список из трех самых любознательных (любознательность определяется количеством курсов).
        System.out.println("\n2. Три самых любознательных студента:\n" +
                studentList.stream()
                        .collect(Collectors.groupingBy(s -> s.getCourses().size()))
                );

        //3. Функция принимает на вход список Student и экземпляр Course и возвращает список студентов, которые посещают этот курс.
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            System.out.println("\n3. Студенты посещающие курс " + course.getName() + ":\n" +
                    studentList.stream()
                            .filter(s -> s.getCourses().contains(course))
                            .collect(Collectors.toList())
            );
        }
    }
}
