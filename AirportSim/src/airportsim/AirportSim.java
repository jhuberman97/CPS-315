package airportsim;
//Jared Huberman
//N03842534
import java.util.*;
public class AirportSim {
    
    static int minutesToLand;
    static int minutesToTakeOff;
    static float arrivalProbability;
    static float departureProbability;
    static int maxAirTime;
    static int totalSimulationMinutes;
    static ArrayDeque<Plane> waitingToLand = new ArrayDeque<Plane>();
    static ArrayDeque<Plane> waitingToTakeOff = new ArrayDeque<Plane>();
    static Plane landing = null;
    static Plane takingOff = null;
    static boolean runwayInUse;
    
    static int planesTakenOff = 0;
    static int planesLanded = 0;
    static int planesCrashed = 0;
    
    static ArrayList<Float> takingOffWaitTimes = new ArrayList<Float>();
    static ArrayList<Float> landingWaitTimes = new ArrayList<Float>();
    
    public static void main(String[] args) {       
        Scanner sc = new Scanner(System.in);
        System.out.print("Amount of minutes to land: ");
        minutesToLand = sc.nextInt();
        System.out.print("Amount of minutes to take off: ");
        minutesToTakeOff = sc.nextInt();
        System.out.print("Probability of arrival during a minute: ");
        arrivalProbability = sc.nextFloat();
        System.out.print("Average amount of time between planes to land: ");
        sc.nextFloat();
        System.out.print("Probability of departure during a minute: ");
        departureProbability = sc.nextFloat();
        System.out.print("Average amount of time between planes to take off: ");
        sc.nextFloat();
        System.out.print("Maximum amount of time in the air before crashing: ");
        maxAirTime = sc.nextInt();
        System.out.print("Total simulation minutes: ");
        totalSimulationMinutes = sc.nextInt(); System.out.println();
        
        for(int i = 1; i <= totalSimulationMinutes; i++){
            for(Plane p : waitingToLand){
                p.maxAirTime--;
                p.minutesFromArrivalToTakeOff++;
                p.minutesFromArrivalToLanding++;
            }
            
            if(Math.random() <= arrivalProbability){
                waitingToLand.add(new Plane(minutesToLand, minutesToTakeOff, maxAirTime));
            }
                        
            if(!waitingToLand.isEmpty() && !runwayInUse){
                    landingWaitTimes.add(waitingToLand.peek().minutesFromArrivalToLanding);
                    landing = waitingToLand.remove();
                    runwayInUse = true;
                }
                else if(!waitingToLand.isEmpty() && waitingToLand.peek().maxAirTime <= 0){
                    waitingToLand.remove();
                    planesCrashed++;
                }
            
            if(landing != null){
                landing.minutesToLand--;
                landing.minutesFromArrivalToLanding++;
                landing.minutesFromArrivalToTakeOff++;
                if(landing.minutesToLand <= 0){
                    waitingToTakeOff.add(landing);
                    landing = null;
                    planesLanded++;
                    runwayInUse = false;
                }
            }
            if(Math.random() <= departureProbability && !waitingToTakeOff.isEmpty()){
                Plane temp = waitingToTakeOff.remove();
                temp.departing = true;
                waitingToTakeOff.addFirst(temp);
            }
            if(!waitingToTakeOff.isEmpty() && !runwayInUse && waitingToLand.isEmpty()
                    && waitingToTakeOff.peek().departing){
                takingOffWaitTimes.add(waitingToTakeOff.peek().minutesFromArrivalToTakeOff);
                takingOff = waitingToTakeOff.remove();
                runwayInUse = true;
            }
            if(takingOff != null){
                takingOff.minutesFromArrivalToTakeOff++;
                takingOff.minutesToTakeOff--;
            }
            if(takingOff != null && takingOff.minutesToTakeOff <= 0){
                takingOff = null;
                planesTakenOff++;
                runwayInUse = false;
            }
        }
        System.out.println("Number of planes taken off: " + planesTakenOff);
        System.out.println("Number of planes landed: " + planesLanded);
        System.out.println("Number of planes crashed: " + planesCrashed);
        Float sum = new Float(0.0);
        float avg;
        for(Float i : takingOffWaitTimes){
            sum += i;
        }
        avg = sum / takingOffWaitTimes.size();
        System.out.println("Average waiting time for taking off: " + avg + " minutes");
        sum = 0.0f;
        for(Float i : landingWaitTimes){
            sum += i;
        }
        avg = sum / landingWaitTimes.size();
        System.out.println("Average waiting time for landing: " + avg + " minutes");
    }
    
}
