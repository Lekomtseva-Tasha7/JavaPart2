package lesson_1;

public class Obstacles {
    private String type;
    private double size;

    public Obstacles(String type, double size) {
        this.type = type;
        this.size = size;

    }

    public String getType() {
        return type;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return type + size;
    }
}
