//package W5;

interface Vehicle {
    abstract void start();
    abstract void stop();
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping...");
    }
}

class Bicycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bicycle is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Bicycle is stopping...");
    }
}