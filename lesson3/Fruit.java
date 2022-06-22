package lesson_3;

public class Fruit {
    private  String type;
    private float weightF;

    public Fruit(String type, float weightF) {
        this.type = type;
        this.weightF = weightF;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeightF() {
        return weightF;
    }

    public void setWeightF(float weightF) {
        this.weightF = weightF;
    }

    @Override
    public String toString() {
        return "Фрукт " + type + " весом " + weightF;
    }
}
