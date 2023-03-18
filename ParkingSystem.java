import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ParkingSystem {
    private int passengerCapacity;
    private double passengerPrice;
    private int lorryCapacity;
    private double lorryPrice;
    private Scanner scanner;
    private HashMap<Integer, Car> passengerCars;
    private HashMap<Integer, Car> lorryCars;
    private Random random;

    public ParkingSystem(Scanner scanner) {
        this.scanner = scanner;
        this.passengerCars = new HashMap<Integer, Car>();
        this.lorryCars = new HashMap<Integer, Car>();
        this.random = new Random();
    }

    public void produceReport() {
        String filename = "parking_report.txt";
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            bw.write("Report generated at " + dateFormat.format(new Date()) + "\n");
            bw.write("Number of passenger cars parked: " + passengerCars.size() + "\n");
            bw.write("Number of lorry cars parked: " + lorryCars.size() + "\n");
            bw.write("Number of vacant passenger car spaces: " + (passengerCapacity - passengerCars.size()) + "\n");
            bw.write("Number of vacant lorry car spaces: " + (lorryCapacity - lorryCars.size()) + "\n");

            bw.write("Parked passenger cars:\n");
            for (HashMap.Entry<Integer, Car> entry : passengerCars.entrySet()) {
                int space = entry.getKey();
                Car car = entry.getValue();
                bw.write("Space " + space + ": arrived at " + dateFormat.format(car.getArrivalTime()) + "\n");
            }

            bw.write("Parked lorry cars:\n");
            for (HashMap.Entry<Integer, Car> entry : lorryCars.entrySet()) {
                int space = entry.getKey();
                Car car = entry.getValue();
                bw.write("Space " + space + ": arrived at " + dateFormat.format(car.getArrivalTime()) + "\n");
            }

            bw.close();
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating report.");
            e.printStackTrace();
        }
    }

    public void parkingCars(int numPassengerCars, int numLorryCars) {
        if (numPassengerCars + numLorryCars > passengerCapacity + lorryCapacity) {
            System.out.println("Not enough parking spaces available.");
            return;
        }

        for (int i = 1; i <= numPassengerCars; i++) {
            int parkSpace = random.nextInt(passengerCapacity) + 1;
            while (passengerCars.containsKey(parkSpace)) {
                parkSpace = random.nextInt(passengerCapacity) + 1;
            }
            Date arrivalTime = new Date();

            Car car = new Car("Passenger", arrivalTime, parkSpace);
            passengerCars.put(parkSpace, car);
        }

        for (int i = 1; i <= numLorryCars; i++) {
            int parkSpace = random.nextInt(lorryCapacity) + 1;
            while (lorryCars.containsKey(parkSpace)) {
                parkSpace = random.nextInt(lorryCapacity) + 1;
            }
            Date arrivalTime = new Date();

            Car car = new Car("Lorry", arrivalTime, parkSpace);
            lorryCars.put(parkSpace, car);
        }

        System.out.println("Cars parked successfully.");
        this.produceReport();
    }

    public void removeCars(int numPassengerCars, int numLorryCars) {
        if (numPassengerCars > passengerCars.size() || numLorryCars > lorryCars.size()) {
            System.out.println("Not enough cars parked to remove.");
            return;
        }

        // remove passenger cars
        for (int i = 1; i <= numPassengerCars; i++) {
            int space = passengerCars.keySet().iterator().next();
            Car car = passengerCars.get(space);
            Date departureTime = new Date();
            long duration = (departureTime.getTime() - car.getArrivalTime().getTime()) / 1000 / 60 / 60;

            double cost = duration * passengerPrice;
            System.out.println(
                    "Car parked in passenger space " + space + " for " + duration + " hours, total cost: " + cost);
            passengerCars.remove(space);
        }

        // remove lorry cars
        for (int i = 1; i <= numLorryCars; i++) {
            int space = lorryCars.keySet().iterator().next();
            Car car = lorryCars.get(space);
            Date departureTime = new Date();
            long duration = (departureTime.getTime() - car.getArrivalTime().getTime()) / 1000 / 60 / 60;

            double cost = duration * lorryPrice;
            System.out
                    .println("Car parked in lorry space " + space + " for " + duration + " hours, total cost: " + cost);
            lorryCars.remove(space);
        }

        System.out.println("Cars removed successfully.");
        this.produceReport();
    }

    public int getFreePassengerCarSpace() {
        return passengerCapacity - passengerCars.size();
    }

    public int getFreeLorryCarSpace() {
        return lorryCapacity - lorryCars.size();
    }

    public double getLorryPrice() {
        return lorryPrice;
    }

    public void setLorryPrice() {
        try {
            System.out.println("Enter the Hourly Fee for the Lorry Car Park: ");
            Double hourlyFeeForLorryCarPark = Double.parseDouble(scanner.nextLine());
            this.lorryPrice = hourlyFeeForLorryCarPark;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.close();
        }
    }

    public int getLorryCapacity() {
        return lorryCapacity;
    }

    public void setLorryCapacity() {
        try {
            System.out.println("Enter Capacity for Lorry Car Park: ");
            Integer capacityOfLorryCarsPark = Integer.parseInt(scanner.nextLine());
            this.lorryCapacity = capacityOfLorryCarsPark;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.close();

        }
    }

    public double getPassengerPrice() {
        return passengerPrice;
    }

    public void setPassengerPrice() {
        try {
            System.out.println("Enter the Hourly Fee for the Passenger Car Park: ");
            Double hourlyFeeForPassengerCarPark = Double.parseDouble(scanner.nextLine());
            this.passengerPrice = hourlyFeeForPassengerCarPark;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.close();
        }
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity() {
        try {
            System.out.println("Enter Capacity for Passenger Car Park: ");
            Integer capacityOfPassengerCarsPark = Integer.parseInt(scanner.nextLine());
            this.passengerCapacity = capacityOfPassengerCarsPark;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.close();
        }
    }
}
