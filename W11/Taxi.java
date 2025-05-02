package W11;

import java.util.Queue;
import java.util.LinkedList;

class DispatchCenter {
    Queue<Passenger> passengerQueue = new LinkedList<>();
    Queue<String> destinationQueue = new LinkedList<>();
    Queue<Taxi> taxiQueue = new LinkedList<>();

    void registerTaxi(Taxi taxi) {
        taxi.dispatch = this;
    }

    void addTaxi(Taxi taxi) {
        taxiQueue.add(taxi);
        assignRide();
    }

    void addRide(Passenger passenger, String destination) {
        passengerQueue.add(passenger);
        destinationQueue.add(destination);
        assignRide();
    }

    void assignRide() {
        if (passengerQueue.peek() != null && taxiQueue.peek() != null) {
            Taxi currentTaxi = taxiQueue.poll();
            currentTaxi.assignedDestination = destinationQueue.poll();
            currentTaxi.assignedPassenger = passengerQueue.poll();
            System.out.printf("Dispatch assigned Taxi %s to passenger %s.\n", currentTaxi.name, currentTaxi.assignedPassenger.name);
        }
    }

    void taxiNotification(Taxi current, boolean rideStatus, Passenger passenger, String destination) {
        if(rideStatus == true) System.out.printf("Taxi %s accepted the ride to %s.\n",current.name ,destination);
        else {
            taxiQueue.add(current);
            passengerQueue.add(passenger);
            destinationQueue.add(destination);
            System.out.printf("Taxi %s rejected the ride to %s. Searching for another taxi...\n", current.name, destination);
            assignRide();
        }
    }
}

class Passenger {
    String name;

    Passenger(String name) {
        this.name = name;
    }

    void requestRide(String destination, DispatchCenter dispatch) {
        System.out.printf("Passenger %s requested a ride to %s.\n", this.name, destination);
        dispatch.addRide(this, destination);
    }
}

public class Taxi {
    String name;
    Passenger assignedPassenger = null;
    String assignedDestination = null;

    // This dispatch variable is handled by the Dispatch Center
    DispatchCenter dispatch;

    Taxi(String name) {
        this.name = name;
    }

    void setAvailable(boolean status) {
        if (status) {
            System.out.printf("Taxi %s is now available.\n", this.name);
            dispatch.addTaxi(this);
        } 
    }

    void respondToRide(boolean response) {
        if(response == true) {
            if (assignedDestination != null) {
                dispatch.taxiNotification(this, true, assignedPassenger, assignedDestination);
            }
        } else {
            dispatch.taxiNotification(this, false, assignedPassenger, assignedDestination);
        }
    }

    public static void main(String[] args) {
        DispatchCenter dispatchCenter = new DispatchCenter();

        Passenger alice = new Passenger("Alice");
        Passenger bob = new Passenger("Bob");

        Taxi taxi1 = new Taxi("Taxi-01");
        Taxi taxi2 = new Taxi("Taxi-02");
        Taxi taxi3 = new Taxi("Taxi-03");

        dispatchCenter.registerTaxi(taxi1);
        dispatchCenter.registerTaxi(taxi2);
        dispatchCenter.registerTaxi(taxi3);

        alice.requestRide("Airport", dispatchCenter);
        bob.requestRide("Downtown", dispatchCenter);

        taxi1.setAvailable(true);
        taxi2.setAvailable(true);
        taxi3.setAvailable(true);

        taxi1.respondToRide(true); // Accept the ride
        taxi2.respondToRide(false); // Reject the ride
        taxi3.respondToRide(true); // Accept the rejected ride
    }
}
