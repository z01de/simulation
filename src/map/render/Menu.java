package map.render;

import java.util.Scanner;

public class Menu {
    public int print() {
        System.out.println("\nSimulation Menu\n");
        System.out.println("1 - Simulate one move");
        System.out.println("2 - Infinite simulation");
        System.out.println("3 - Exit");
        System.out.print("Select one item: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
