import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Vehicle interface
interface Vehicle {
  public String getMake();

  public String getModel();

  public int getYear();

  public void setMake(String make);

  public void setModel(String model);

  public void setYear(int year);
}

// CarVehicle interface extending Vehicle with car-specific methods
interface CarVehicle extends Vehicle {
  public int getDoors();

  public String getFuelType();

  public void setDoors(int doors);

  public void setFuelType(String fuelType);
}

// MotorVehicle interface extending Vehicle with motorcycle-specific methods
interface MotorVehicle extends Vehicle {
  public int getWheels();

  public String getType();

  public void setWheels(int wheels);

  public void setType(String type);
}

// TruckVehicle interface extending Vehicle with truck-specific methods
interface TruckVehicle extends Vehicle {
  public double getCargoCapacity();

  public String getTransmissionType();

  public void setCargoCapacity(double cargoCapacity);

  public void setTransmissionType(String transmissionType);
}

// Car class implementing CarVehicle interface
class Car implements CarVehicle {
  // Class variables
  private String make;
  private String model;
  private int year;
  private int doors;
  private String fuelType;
  private int id;

  // Constructor for creating each instance of object
  public Car(String make, String model, int year, int doors, String fuelType) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.doors = doors;
    this.fuelType = fuelType;
    this.id = CarRentalSoftware.vehicles.size() + 1;
  }

  // Getter and setter methods
  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public int getDoors() {
    return doors;
  }

  public String getFuelType() {
    return fuelType;
  }

  public int getID() {
    return id;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setDoors(int doors) {
    this.doors = doors;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }
}

// Motorcycle class implementing MotorVeheicle interface
class Motorcycle implements MotorVehicle {
  // Class variables
  private String make;
  private String model;
  private int year;
  private int wheels;
  private String type;
  private int id;

  // Constructor for creating each instance of Motorcycle object
  public Motorcycle(String make, String model, int year, int wheels, String type) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.wheels = wheels;
    this.type = type;
    this.id = CarRentalSoftware.vehicles.size() + 1;
  }

  // Getter and setter methods
  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public int getWheels() {
    return wheels;
  }

  public String getType() {
    return type;
  }

  public int getID() {
    return id;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setWheels(int wheels) {
    this.wheels = wheels;
  }

  public void setType(String type) {
    this.type = type;
  }
}

class Truck implements TruckVehicle {
  private String make;
  private String model;
  private int year;
  private double cargoCapacity;
  private String transmissionType;
  private int id;

  public Truck(String make, String model, int year, double cargoCapacity, String transmissionType) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.cargoCapacity = cargoCapacity;
    this.transmissionType = transmissionType;
    this.id = CarRentalSoftware.vehicles.size() + 1;
  }

  // Getter and setter methods
  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public double getCargoCapacity() {
    return cargoCapacity;
  }

  public String getTransmissionType() {
    return transmissionType;
  }

  public int getID() {
    return id;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setCargoCapacity(double cargoCapacity) {
    this.cargoCapacity = cargoCapacity;
  }

  public void setTransmissionType(String transmissionType) {
    this.transmissionType = transmissionType;
  }
}

public class CarRentalSoftware {
  public static List<Vehicle> vehicles = new ArrayList<>(); // List to store all vehicles

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("\n Vehicle commands:");
        System.out.println("1. Add a new Car");
        System.out.println("2. Add a new Motorcycle");
        System.out.println("3. Add a new Truck");
        System.out.println("4. Display all vehicles of a type");
        System.out.println("5. Enter the type of vehicle to edit (Car, Motorcycle, Truck):");
        System.out.println("0. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        String type;

        switch (choice) {
          case 1:
            System.out.println("Enter make, model, year, number of doors, and fuel type for the car:");
            Car car = new Car(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.next());
            System.out.println("Car added: " + car.getMake() + ", " + car.getModel() + ", " + car.getYear() + ", "
                + car.getDoors() + ", " + car.getFuelType());
            vehicles.add(car); // Add the new car to the list
            break;
          case 2:
            System.out.println("Enter make, model, year, number of wheels, and type for the motorcycle:");
            Motorcycle motorcycle = new Motorcycle(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(),
                scanner.next());
            System.out.println("Motorcycle added: " + motorcycle.getMake() + ", " + motorcycle.getModel() + ", "
                + motorcycle.getYear() + ", " + motorcycle.getWheels() + ", " + motorcycle.getType());
            vehicles.add(motorcycle); // Add the new motorcycle to the list
            break;
          case 3:
            System.out.println("Enter make, model, year, cargo capacity, and transmission type for the truck:");
            Truck truck = new Truck(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextDouble(),
                scanner.next());
            System.out
                .println("Truck added: " + truck.getMake() + ", " + truck.getModel() + ", " + truck.getYear() + ", "
                    + truck.getCargoCapacity() + ", " + truck.getTransmissionType());
            vehicles.add(truck); // Add the new truck to the list
            break;
          case 4:
            System.out.println("Enter the type of vehicle to display (Car, Motorcycle, Truck):");
            type = scanner.next();
            displayVehiclesOfType(type);
            break;
          case 5: // case for editing a vehicle
            System.out.println("Enter the type of vehicle to edit (Car, Motorcycle, Truck):");
            type = scanner.next();
            System.out.println("Enter the id of the vehicle to edit:");
            int id = scanner.nextInt();
            if (id < 0 || id >= vehicles.size() + 1) {
              System.out.println("Invalid id.");
              break;
            }
            Vehicle vehicle = vehicles.get(id - 1);
            if (vehicle.getClass().getSimpleName().equalsIgnoreCase(type)) {
              if (vehicle instanceof Car) {
                Car updatedCar = (Car) vehicle;
                System.out.println("Enter new make, model, year, number of doors, and fuel type for the car:");
                updatedCar.setMake(scanner.next());
                updatedCar.setModel(scanner.next());
                updatedCar.setYear(scanner.nextInt());
                updatedCar.setDoors(scanner.nextInt());
                updatedCar.setFuelType(scanner.next());
              } else if (vehicle instanceof Motorcycle) {
                Motorcycle updatedMotorcycle = (Motorcycle) vehicle;
                System.out.println("Enter new make, model, year, number of wheels, and type for the motorcycle:");
                updatedMotorcycle.setMake(scanner.next());
                updatedMotorcycle.setModel(scanner.next());
                updatedMotorcycle.setYear(scanner.nextInt());
                updatedMotorcycle.setWheels(scanner.nextInt());
                updatedMotorcycle.setType(scanner.next());
              } else if (vehicle instanceof Truck) {
                Truck updatedTruck = (Truck) vehicle;
                System.out.println("Enter new make, model, year, cargo capacity, and transmission type for the truck:");
                updatedTruck.setMake(scanner.next());
                updatedTruck.setModel(scanner.next());
                updatedTruck.setYear(scanner.nextInt());
                updatedTruck.setCargoCapacity(scanner.nextDouble());
                updatedTruck.setTransmissionType(scanner.next());
              }
            }
            break;
          case 0:
            System.out.println("Exiting program...");
            System.exit(0); // Exit the program
          default:
            System.out.println("Invalid choice. Please try again."); // Handle invalid choices
        }
      }
    }
  }

  private static void displayVehiclesOfType(String type) {
    switch (type.toLowerCase()) {
      case "car":
        System.out.println("ID | Make | Model | Year | # doors | Fuel");
        break;
      case "motorcycle":
        System.out.println("ID | Make | Model | Year | # Wheels | Type");
        break;
      case "Truck":
        System.out.println("ID | Make | Model | Year | Cargo Capacity | Transmission type");
        break;
      default:
        System.out.println("Invalid type.");
        return;

    }
    for (Vehicle vehicle : vehicles) {
      if (vehicle.getClass().getSimpleName().equalsIgnoreCase(type)) {
        if (vehicle instanceof Car) {
          Car car = (Car) vehicle;
          System.out.println(car.getID() + ", " + car.getMake() + ", " + car.getModel() + ", " + car.getYear() + ", "
              + car.getDoors() + ", " + car.getFuelType());
        } else if (vehicle instanceof Motorcycle) {
          Motorcycle motorcycle = (Motorcycle) vehicle;
          System.out.println(motorcycle.getID() + ", " + motorcycle.getMake() + ", " + motorcycle.getModel() + ", "
              + motorcycle.getYear() + ", " + motorcycle.getWheels() + ", " + motorcycle.getType());
        } else if (vehicle instanceof Truck) {
          Truck truck = (Truck) vehicle;
          System.out
              .println(truck.getID() + ", " + truck.getMake() + ", " + truck.getModel() + ", " + truck.getYear() + ", "
                  + truck.getCargoCapacity() + ", " + truck.getTransmissionType());
        }
      }
    }
  }
}