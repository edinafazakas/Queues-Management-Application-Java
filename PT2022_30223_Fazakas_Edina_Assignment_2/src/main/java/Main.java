import BusinessLogic.SimulationManager;
import View.GUI;

public class Main {

    public static void main(String[]args) throws Exception {
        //BusinessLogic.Generate g = new BusinessLogic.Generate();
        //g.print();
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
        //new GUI();
        //gen.averageTime();
    }
}
