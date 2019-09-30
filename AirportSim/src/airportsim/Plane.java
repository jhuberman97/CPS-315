package airportsim;
//Jared Huberman
//N03842534
public class Plane {
    
    int minutesToLand;
    int minutesToTakeOff;
    float minutesFromArrivalToTakeOff = 0;
    float minutesFromArrivalToLanding = 0;
    boolean departing = false;
    int maxAirTime;
    
    public Plane(int l, int t, int x){
        minutesToLand = l;
        minutesToTakeOff = t;
        maxAirTime = x;
    }
    
}
