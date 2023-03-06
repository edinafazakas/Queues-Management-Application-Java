package Model;

public class Task implements Comparable<Task>{
    private int id;
    private int arrivalTime;
    private int serviceTime;
    public int service = 0;
    public int ok = 0;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Task(){

    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(getArrivalTime(), o.getArrivalTime());
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
