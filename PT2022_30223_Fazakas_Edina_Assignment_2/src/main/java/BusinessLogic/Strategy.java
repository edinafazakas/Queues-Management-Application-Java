package BusinessLogic;
import Model.Server;
import Model.Task;
import java.util.Collections;
import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}


class ConcreteStrategyQueue implements Strategy{
    @Override
    public synchronized void addTask(List<Server> servers, Task t) {
        for(Server s : servers)
            if (s.getTasks().isEmpty()) {
                s.getTasks().add(t);
                t.ok = 1;
                //s.getWaitingPeriod().incrementAndGet();
                //s.setWaitingPeriod(s.getWaitingPeriod() + 1);
                break;
            }
            if (t.ok == 0)
                t.setArrivalTime(t.getArrivalTime() + 1);
    }
}

class ConcreteStrategyTime implements Strategy{
    @Override
    public synchronized void addTask(List<Server> servers, Task t) {
        Collections.sort(servers);
        for(Server s: servers)
            if(s.getTasks().isEmpty()){
                s.getTasks().add(t);
                //s.getWaitingPeriod().incrementAndGet();
                //s.setWaitingPeriod(s.getWaitingPeriod() + 1);
                t.ok = 1;
                break;
            }
        if (t.ok == 0)
            t.setArrivalTime(t.getArrivalTime() + 1);
    }
}

