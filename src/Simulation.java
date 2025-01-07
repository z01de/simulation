import map.SimulationMap;
import map.render.Menu;
import actions.*;
import map.render.Renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private static final int SIMULATE_ONE_MOVE = 1;
    private static final int INFINITE_SIMULATION = 2;
    private static final int EXIT = 3;

    public void start(SimulationMap simulationMap){
        Scanner scanner = new Scanner(System.in);
        Renderer renderer = new Renderer();
        initActions(simulationMap);
        renderer.render(simulationMap);
        Menu menu = new Menu();
        int stepCounter = 0;
        switch (menu.print()){
            case SIMULATE_ONE_MOVE -> {
                stepCounter++;
                System.out.println("Step: " + stepCounter);
                turnActions(simulationMap);
                renderer.render(simulationMap);
            }
            case INFINITE_SIMULATION -> {
                while (true) {
                    stepCounter++;
                    System.out.println("Step: " + stepCounter);
                    turnActions(simulationMap);
                    renderer.render(simulationMap);
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
            case EXIT -> {
                System.exit(EXIT);
            }
        }
    }

    public static void initActions(SimulationMap simulationMap) {
        List<Action> initActions = new ArrayList<>(Arrays.asList(
                new HerbivoreGenerateAction(),
                new PredatorGenerateAction(),
                new TreeGenerateAction(),
                new GrassGenerateAction(),
                new RockGenerateAction()
        ));
        for(Action action : initActions) {
            action.perform(simulationMap);
        }

    }
    public static void turnActions(SimulationMap simulationMap) {
        List<Action> turnActions = new ArrayList<>(Arrays.asList(
                new TurnAction(),
                new RefillerAction()
        ));
        for(Action action : turnActions) {
            action.perform(simulationMap);
        }
    }
}
