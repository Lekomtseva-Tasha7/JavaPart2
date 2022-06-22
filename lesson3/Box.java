package lesson_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Box<T extends Fruit> {
    private ArrayList<T> listOfFruits;

    public Box(ArrayList<T> listOfFruits) {
        this.listOfFruits = listOfFruits;
    }

    public ArrayList<T> getListOfFruits() {
        return listOfFruits;
    }

    public void setListOfFruits(ArrayList<T> listOfFruits) {
        this.listOfFruits = listOfFruits;
    }

    public boolean compare(Box<?> box) {
        return getWeight() == box.getWeight();
    }

    public float getWeight() {
        if (!getListOfFruits().isEmpty()) {
            return (float)getListOfFruits().size() * getListOfFruits().get(0).getWeightF();
        } else return 0.0f;
    }

    public void addingFruits(){
        int x;
        Scanner scanner = new Scanner(System.in);
        do {
            x = scanner.nextInt();
        } while (x < 0);
        ArrayList<T> newList = new ArrayList<>();
        newList.addAll(getListOfFruits());
        for (int i = 0; i < x; i++){
            newList.add(getListOfFruits().get(0));
        }
        setListOfFruits(newList);
    }

    public void removeFruits(Box<T> box){
            ArrayList<T> newList = new ArrayList<>();
            ArrayList<T> newList2 = new ArrayList<>();
            newList.addAll(getListOfFruits());
            newList.addAll(box.getListOfFruits());
            setListOfFruits(newList);
            box.setListOfFruits(newList2);
    }

    public void printBox(Box<Orange> box2, Box<Apple> box3){
        System.out.println("Коробка №1 с яблоками: " + getListOfFruits().size() + " шт., вес =  " + getWeight() +
                            "\nКоробка №2 с апельсинами: " + box2.getListOfFruits().size() + " шт., вес =  " + box2.getWeight() +
                            "\nКоробка №3 с яблоками: " + box3.getListOfFruits().size() + " шт., вес = " + box3.getWeight());
    }
}