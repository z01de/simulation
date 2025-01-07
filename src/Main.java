import map.SimulationMap;

public static void main(String[] args) {
    SimulationMap simulationMap = new SimulationMap(10, 10);
    Simulation simulation = new Simulation();
    simulation.start(simulationMap);
}
