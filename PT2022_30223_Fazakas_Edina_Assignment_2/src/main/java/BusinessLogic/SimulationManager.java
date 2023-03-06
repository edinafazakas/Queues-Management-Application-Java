package BusinessLogic;
import Model.Server;
import Model.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SimulationManager implements Runnable{
    //producer
    public int timeLimit = 60;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 30;
    public int minProcessingTime = 2;
    public int maxProcessingTime = 4;
    public int numberOfServers = 2;
    public int numberOfClients = 4;
    public SelectionPolicy selectionPolicy;
    private final List<Task> generatedTask;
    private final Scheduler scheduler;
    double serviceAverageTime = 0;
    double waitingAverageTime = 0;

    public SimulationManager() {
        scheduler = new Scheduler(numberOfServers);
        selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        scheduler.changeStrategy(selectionPolicy);
        Generate g = new Generate();
        generatedTask = g.Generate(minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime);
        System.out.println("\nRandom generated tasks\n");
        try {
            FileWriter myWriter = new FileWriter("file.txt");
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
        try {
            FileWriter myWriter = new FileWriter("file.txt", true);
            myWriter.write("\nRandom generated tasks\n\n");
            myWriter.close();
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();
        }
        for (Task t : generatedTask) {
            System.out.println("id:" + t.getId() + "     arrivalTime:" + t.getArrivalTime() + "     serviceTime:" + t.getServiceTime());
            try {
                FileWriter myWriter = new FileWriter("file.txt", true);
                myWriter.write("id:" + t.getId() + "       arrivalTime: " + t.getArrivalTime() + "      serviceTime:" + t.getServiceTime()  + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }


    @Override
    public synchronized void run() {
        int currentTime = 0;
        System.out.println("\n\n* Customers in services *\n");
        try {
            FileWriter myWriter = new FileWriter("file.txt", true);
            myWriter.write("\n\n* Customers in services *\n\n"); myWriter.close();
        }
        catch (IOException e) { e.printStackTrace();}
        while (currentTime < timeLimit) {
            System.out.println(" - current time: " + currentTime);
            try { FileWriter myWriter = new FileWriter("file.txt", true);
                  myWriter.write(" - current time: " + currentTime + "\n");
                  myWriter.close();
            }
            catch (IOException e) { e.printStackTrace(); }
            for (Task t : generatedTask)
                if (t.getArrivalTime() == currentTime && t.service == 0) {
                    scheduler.dispatchTask(t);
                    t.service = 1;
                    serviceAverageTime += t.getServiceTime();
                }
            currentTime++;
            if(currentTime == timeLimit) {
                System.out.println("\n\n Average waiting time: " + waitingAverageTime / numberOfServers);
                System.out.println(" Average service time: " + serviceAverageTime / numberOfClients);
                try {
                    FileWriter myWriter = new FileWriter("file.txt", true);
                    myWriter.write("\n\n Average waiting time: " + waitingAverageTime / numberOfServers);
                    myWriter.write("\n Average service time: " + serviceAverageTime / numberOfClients);
                    myWriter.close();} catch (IOException e) {
                    e.printStackTrace();
                }
                break;}
            for(Server s: scheduler.getServers()) {
                Thread t = new Thread(s);
                t.start();
                waitingAverageTime = waitingAverageTime +  s.getWaitingPeriod();
                if (!(s.getTasks().isEmpty())) {
                    if (s.getTasks().peek().getServiceTime() == 0)
                        s.getTasks().remove();
                    s.setWaitingPeriod(s.getWaitingPeriod() + 1);
                }
            }
            try
            { Thread.sleep(1000); //thread sleeping for 1 second
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }
}
