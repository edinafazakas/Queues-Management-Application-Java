package BusinessLogic;

import Model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generate{
    int processingTime;
    int arrivalTime;
    int N = 4;
    ArrayList<Task> task;
    int id;
    int serviceTime;

    public ArrayList<Task> Generate(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime){
        Random random = new Random();
        processingTime = random.nextInt(1000);
        task = new ArrayList<>();
        int i = 0;
        Task task1;
        while(i < N){

            serviceTime = random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime; //[minService, maxService]
            id = i;
            arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            task1 = new Task(id, arrivalTime, serviceTime);
            task.add(task1);
            i++;
        }
        Collections.sort(task);
        return task;
    }

    /*public void print(){
        System.out.println("N = " + N);
        for(Model.Task t: task)
            System.out.println("id: " + t.getId() + " arrivalTime: " + t.getArrivalTime() + " serviceTime: " + t.getServiceTime());
        System.out.println("processingTime " + processingTime);
    }
*/

}
