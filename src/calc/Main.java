package src.calc;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        Thread EDT = new Thread(gui);
        EDT.start();
    }
}