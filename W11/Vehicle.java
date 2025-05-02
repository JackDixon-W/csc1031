package W11;

abstract class Vehicle {
    String brand;
    Engine vehicleEngine;

    Vehicle(String brand, Engine vehicleEngine) {
        this.brand = brand;
        this.vehicleEngine = vehicleEngine;
    }

    abstract void startEngine();

    public static void main(String[] args) {
        Engine carEngine = new Engine(150);
        Car myCar = new Car("Toyota", 4, carEngine);
        myCar.startEngine();

        Engine bikeEngine = new Engine(20);
        Bike myBike = new Bike("Yamaha", true, bikeEngine);
        myBike.startEngine();

        Engine electricCarEngine = new Engine(200);
        ElectricCar tesla = new ElectricCar("Tesla", 4, 75, electricCarEngine);
        tesla.startEngine();
    }
}

class Car extends Vehicle {
    int numDoors;

    Car(String brand, int numDoors, Engine vehicleEngine) {
        super(brand, vehicleEngine);
        this.numDoors = numDoors;
    }

    void startEngine() {
        System.out.println("Starting car with " + vehicleEngine.horsepower + " horsepowers");
    }
}

class ElectricCar extends Car {
    int batteryCapacity;

    ElectricCar(String brand, int numDoors, int batteryCapacity, Engine vehicleEngine) {
        super(brand, numDoors, vehicleEngine);
        this.batteryCapacity = batteryCapacity;
    }

    void startEngine() {
        System.out.println("Starting electric car silently with " + vehicleEngine.horsepower + " horsepowers");
    }
}

class Bike extends Vehicle {
    boolean hasCarrier;

    Bike(String brand, boolean hasCarrier, Engine vehicleEngine) {
        super(brand, vehicleEngine);
        this.hasCarrier = hasCarrier;
    }

    void startEngine() {
        System.out.println("Starting bike with " + vehicleEngine.horsepower + " horsepowers");
    }
}

class Engine {
    int horsepower;

    Engine(int horsepower) {
        this.horsepower = horsepower;
    }
}
