package BusinessLogic;
import Model.Server;
import Model.Task;
import java.util.*;

public class Scheduler {
    private final List<Server> servers;
    private int maxNoServers;
    private Strategy strategy;

    //gives tasks
    public Scheduler(int maxNoServers) {
        this.maxNoServers = maxNoServers;

        servers = Collections.synchronizedList(new ArrayList<Server>());
        int i = 0;
        while(i < maxNoServers) {
            Server s1 = new Server(i);
            //Thread t = new Thread(s1);
            servers.add(s1);
            //t.start();
            i++;
        }
    }

    public synchronized void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();

        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }

    public synchronized void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }


    public synchronized List<Server> getServers(){
        return servers;
    }
}
