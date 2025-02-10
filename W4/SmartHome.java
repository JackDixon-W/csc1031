public class SmartHome {

    static class Appliance {
        private String brand;
        private double powerConsumption; // Measured in watts

        Appliance(String brand, double power)
        {
            this.brand = brand;
            this.powerConsumption = power;
        }

        protected void turnOn()
        {
            System.out.println("Turning on " + this.brand + " appliance");
        }

        protected void turnOff()
        {
            System.out.println("Turning off " + this.brand + " appliance");
        }

        public String getBrand()
        {
            return this.brand;
        }

        public double getPowerConsumption()
        {
            return this.powerConsumption;
        }
    }

    static class WashingMachine extends Appliance{
        private int drumSize;

        WashingMachine(String brand, double power, int drumSize){
            super(brand, power);
            this.drumSize = drumSize;
        }

        public int getDrumSize(){
            return this.drumSize;
        }

        public void washClothes(){
            System.out.println("Washing clothes in a " + this.getBrand() + " washing machine");
        }
    }

    static class Refrigerator extends Appliance{
        private double temperature;

        Refrigerator(String brand, double power, double temp)
        {
            super(brand, power);
            this.temperature = temp;
        }

        public double getTemperature(){
            return this.temperature;
        }

        public void setTemperature(double temperature)
        {
            this.temperature = temperature;
        }

        public void coolItems()
        {
            System.out.println("Cooling items in a " + this.getBrand() + " refrigerator at "
                                + this.temperature + "°C");
        }
    }

    static class SmartWashingMachine extends WashingMachine{
        private boolean hasWifi;

        SmartWashingMachine(String brand, double power, int drumSize, boolean hasWifi)
        {
            super(brand, power, drumSize);
            this.hasWifi = hasWifi;
        }

        public boolean hasWiFi(){
            return this.hasWifi;
        }

        public void connectToWiFi(){
            if (hasWifi){
                System.out.println("Smart Washing Machine connected to WiFi");
            } else {
                System.out.println("This washing machine does not support WiFi");
            }
            
        }
    }
}