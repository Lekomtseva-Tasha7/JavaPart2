package lesson_4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Phonebook {
    //2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.
    //В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
    //номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
    //(в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
    private String surname;
    private String phone;

    public Phonebook(String surname, String phone) {
        this.surname = surname;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return surname + " тел. " + phone;
    }

    public static void main(String[] args) {
        List<Phonebook> phonebook = new ArrayList<>();
        phonebook.add(new Phonebook("Смирнов", "+7 (902) 40-90-120"));
        phonebook.add(new Phonebook("Аверьякова", "+7 (902) 40-90-121"));
        phonebook.add(new Phonebook("Пермякова", "+7 (902) 40-90-122"));
        phonebook.add(new Phonebook("Смирнов", "+7 (902) 40-90-123"));
        phonebook.add(new Phonebook("Смирнов", "+7 (902) 40-90-124"));

       // 1 вариант
        for (int i = 0; i < phonebook.size(); i++) {
            if (phonebook.get(i).getSurname() == "Смирнов") System.out.println(phonebook.get(i));
        }

        // 2 вариант
        Iterator<Phonebook> phonebookIterator = phonebook.iterator();
        while (phonebookIterator.hasNext()) {
            Phonebook phonebookX = phonebookIterator.next();
            if (phonebookX.getSurname().equals("Аверьякова")) {
                System.out.println(phonebookX);
            }
        }
    }
}
