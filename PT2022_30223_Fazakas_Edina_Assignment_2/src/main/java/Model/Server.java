package Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable, Comparable<Server>{

    private BlockingQueue<Task> tasks;
    private int waitingPeriod;
    int i;

    //consumer

    public Server(int i) {
        tasks = new ArrayBlockingQueue<Task>(1);
        //waitingPeriod = new AtomicInteger(0);
        waitingPeriod = 0;
        this.i = i;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }


    public int getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    @Override
    public synchronized void run() {
        Task head = tasks.peek();
        try {
            FileWriter myWriter = new FileWriter("file.txt", true);
            if (head != null) {
                System.out.println("\n          * server " + i);
                System.out.println("\n            id:" + head.getId() + "   service time:" + head.getServiceTime() + "   arrival time:" + head.getArrivalTime());
                myWriter.write("\n          * server " + i + "\n");
                myWriter.write("\n            id:" + head.getId() + "   service time:" + head.getServiceTime() + "   arrival time:" + head.getArrivalTime() +  "\n");
                myWriter.close();
            }
            else {
                System.out.println("\n         * server " + i);
                myWriter.write("\n         * server " + i + "\n");
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (head != null) {
            if (head.getServiceTime() > 0) {
                head.setServiceTime(head.getServiceTime() - 1);
            }
            waitingPeriod--;
        }
    }

    @Override
    public synchronized int compareTo(Server o) {
        //return Integer.compare(getWaitingPeriod().get(), o.getWaitingPeriod().get());
        return Integer.compare(getWaitingPeriod(), o.getWaitingPeriod());

    }
}

