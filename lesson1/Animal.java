package lesson_1;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Animal implements CanSwim, CanJump, CanRun  {
    private String type;
    private String name;
    private double age;
    private double swimmingSpeed;
    private double jumpingHeight;
    private double runningSpeed;
    private double swimmingLength;
    private double runningLength;

    public Animal(String type, String name, int age, double swimmingSpeed, double jumpingHeight, double runningSpeed, double swimmingLength, double runningLength) {
        this.type = type;
        this.name = name;
        this.setAge(age);
        this.swimmingSpeed = swimmingSpeed;
        this.jumpingHeight = jumpingHeight;
        this.runningSpeed = runningSpeed;
        this.swimmingLength = swimmingLength;
        this.runningLength = runningLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            System.out.println("Некорректное значение!");
        } else {
            this.age = age;
        }
    }

    public String getType() {
        return type;
    }

    public double getSwimmingSpeed() {
        return swimmingSpeed;
    }

    public double getJumpingHeight() {
        return jumpingHeight;
    }

    public double getRunningSpeed() {
        return runningSpeed;
    }

    public double getSwimmingLength() {
        return swimmingLength;
    }

    public double getRunningLength() {
        return runningLength;
    }

    DecimalFormat df = new DecimalFormat("###.##");

    public double swim(Pool pool) {
        if (pool.getSize() <= getSwimmingLength()) {
            System.out.println( "   Переплыл бассейн за " + df.format(pool.getSize() / getSwimmingSpeed()) + " мин.");
            return pool.getSize() / getSwimmingSpeed();
        } else {
            System.out.println("   Не может так далеко плавать...");
            return 0;
        }
    }

    public double jump(Fence fence) {
        if (fence.getSize() > getJumpingHeight()) {System.out.println("   Не смог так высоко запрыгнуть...");
            return 0;
        } else {
            System.out.println( "   Перепрыгнул забор за 1 мин.");
            return 1;
        }
    }

    public double run(Trail trail) {
        if (trail.getSize() <= getRunningLength()) {
            System.out.println("   Пробежал дистанцию за " + df.format(trail.getSize() / getRunningSpeed()) + " мин.");
            return trail.getSize() / getRunningSpeed();
        } else {System.out.println("   Не может так далеко бегать...");
            return 0;
        }
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}