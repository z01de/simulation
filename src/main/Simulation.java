package main;

import auxiliary.Actions;
import auxiliary.Renderer;
import world.WorldMap;

import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        Renderer renderer = new Renderer();
        WorldMap worldMap = new WorldMap(10,10);
        Actions.initActions(worldMap,10,10,10,10);
        Scanner scanner = new Scanner(System.in);
        while (true) {
        System.out.println("\nSimulation Menu");
            System.out.println("___________________________");
            System.out.println("1. Simulate one move");
            System.out.println("2. Infinite simulation");
            System.out.println("3. Exit");
            System.out.println("___________________________");
            System.out.print("Select one item: ");
            int item = scanner.nextInt();
            System.out.println();
            switch (item) {
                case 1:
                    Actions.turnActions(worldMap);
                    break;
                case 2:
                    while (true) {
                        Actions.turnActions(worldMap);
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){}
                    }
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("There is no such menu item.");
            }
        }
    }
}
