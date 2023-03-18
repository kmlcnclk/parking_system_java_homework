import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ParkingSystem parkingSystem = new ParkingSystem(scanner);

        parkingSystem.setLorryCapacity();
        parkingSystem.setLorryPrice();
        parkingSystem.setPassengerCapacity();
        parkingSystem.setPassengerPrice();

        System.out.println(parkingSystem.getLorryCapacity());
        System.out.println(parkingSystem.getLorryPrice());

        System.out.println(parkingSystem.getPassengerCapacity());
        System.out.println(parkingSystem.getPassengerPrice());

        while (true) {
            System.out.println("Enter 1 for Learn Empty Park Spaces for Passenger Cars and Lorry Cars");
            System.out.println("Enter 2 for Parking Cars");
            System.out.println("Enter 3 for Remove the cars from park places");
            System.out.println("Enter 4 for Close The Program");

            Integer process = Integer.parseInt(scanner.nextLine());

            if (process == 1) {
                System.out.println("Free Passenger Spaces are: ");
                System.out.println(parkingSystem.getFreePassengerCarSpace());

                System.out.println("Free Lorry Spaces are: ");
                System.out.println(parkingSystem.getFreeLorryCarSpace());
            } else if (process == 2) {
                System.out.println("Enter Number of Passenger Cars");
                Integer numPassengerCars = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter Number of Lorry Cars");
                Integer numLorryCars = Integer.parseInt(scanner.nextLine());

                parkingSystem.parkingCars(numPassengerCars, numLorryCars);
            } else if (process == 3) {
                System.out.println("Enter Number of Passenger Cars");
                Integer numPassengerCars = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter Number of Lorry Cars");
                Integer numLorryCars = Integer.parseInt(scanner.nextLine());

                parkingSystem.removeCars(numPassengerCars, numLorryCars);
            } else if (process == 4) {
                System.out.println("Parking System is closing");
                break;
            } else {
                System.out.println("You should enter 1, 2, 3 or 4");
            }

        }
        scanner.close();
    }
}
