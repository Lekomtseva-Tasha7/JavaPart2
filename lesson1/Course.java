package lesson_1;

public class Course {
    private double sizePool;
    private double sizeFence;
    private double sizeTrail;
    private Obstacles [] obstacles = new Obstacles [3];
    private double [] time;
    private double timeSwim;
    private double timeJump;
    private double timeRun;

    public Course(double sizePool, double sizeFence, double sizeTrail) {
        this.sizePool = sizePool;
        this.sizeFence = sizeFence;
        this.sizeTrail = sizeTrail;
        obstacles[0] = new Pool(sizePool);
        obstacles[1] = new Fence(sizeFence);
        obstacles[2] = new Trail(sizeTrail);
    }

    public Obstacles[] getObstacles() {
        return obstacles;
    }

    public double[] getTime() {
        return time;
    }

    public void showCourse(){
        System.out.println("\nПолоса препятствий:");
        for (Obstacles obstacle : obstacles) {
            System.out.println("   " + obstacle.getType() + " " + obstacle.getSize() + " м,");
        }
    }

    public void doIt(Team team){
        time = new double[team.getAnimal().length];
       System.out.println("\nПрошу членов команды " + team.getTeamName() + " пройти полосу препятствий:");
       for (int i = 0; i < team.getAnimal().length; i++) {
           System.out.println(team.getAnimal()[i]);
           timeSwim = team.getAnimal()[i].swim((Pool) getObstacles()[0]);
           timeJump = team.getAnimal()[i].jump((Fence) getObstacles()[1]);
           timeRun = team.getAnimal()[i].run((Trail) getObstacles()[2]);
           time[i] = timeSwim + timeJump + timeRun;
           if (timeSwim == 0 || timeJump == 0 || timeRun == 0) {
               team.getAnimal()[i] = null;
           }
       }
     }
}
