class Ride {
    int rideId;
    String pickup;
    String drop;
    double fare;

    Ride(int rideId, String pickup, String drop, double fare) {
        this.rideId = rideId;
        this.pickup = pickup;
        this.drop = drop;
        this.fare = fare;
    }
}

class Node {
    Ride data;
    Node next;

    Node(Ride data) {
        this.data = data;
        this.next = null;
    }
}

class RideHistory {
    Node head;


    void addRide(Ride r) {
        Node newNode = new Node(r);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }


    void deleteLastRide() {
        if (head == null) {
            System.out.println("No rides to delete");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
    }


    void displayRides() {
        Node temp = head;
        while (temp != null) {
            System.out.println("RideID: " + temp.data.rideId +
                    ", Pickup: " + temp.data.pickup +
                    ", Drop: " + temp.data.drop +
                    ", Fare: " + temp.data.fare);
            temp = temp.next;
        }
    }


    void searchRide(String location) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.pickup.equals(location) || temp.data.drop.equals(location)) {
                System.out.println("Found Ride -> ID: " + temp.data.rideId);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("No ride found for location: " + location);
        }
    }


    void reverseHistory() {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
    }
}

public class Main1 {
    public static void main(String[] args) {
        RideHistory history = new RideHistory();

        history.addRide(new Ride(1, "Bangalore", "Mysore", 500));
        history.addRide(new Ride(2, "Delhi", "Agra", 300));
        history.addRide(new Ride(3, "Mumbai", "Pune", 400));

        System.out.println("All Rides:");
        history.displayRides();

        System.out.println("\nSearch:");
        history.searchRide("Agra");

        System.out.println("\nDelete Last Ride:");
        history.deleteLastRide();
        history.displayRides();

        System.out.println("\nReverse History:");
        history.reverseHistory();
        history.displayRides();
    }
}
