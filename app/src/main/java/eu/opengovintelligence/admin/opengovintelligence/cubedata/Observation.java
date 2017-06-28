package eu.opengovintelligence.admin.opengovintelligence.cubedata;

/**
 * Created by Admin on 29/6/2017.
 */

public class Observation {
    String data,day,time,id;
    int avg_cost,number_of_crashes,total_cost;

    public Observation(String data, String day, String time, String id, int avg_cost, int number_of_crashes, int total_cost) {
        this.data = data;
        this.day = day;
        this.time = time;
        this.id = id;
        this.avg_cost = avg_cost;
        this.number_of_crashes = number_of_crashes;
        this.total_cost = total_cost;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvg_cost() {
        return avg_cost;
    }

    public void setAvg_cost(int avg_cost) {
        this.avg_cost = avg_cost;
    }

    public int getNumber_of_crashes() {
        return number_of_crashes;
    }

    public void setNumber_of_crashes(int number_of_crashes) {
        this.number_of_crashes = number_of_crashes;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
}
