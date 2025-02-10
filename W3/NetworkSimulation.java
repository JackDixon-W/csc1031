import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
 * Runtime issue on line 109, remClient(tower) cannot work because tower is null
 * But it only gets called once and it shouldn't be null?
 */

public class NetworkSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Network net = new Network();
        String curLine, command;

        // Temporary variables for handling inputs
        int iLine1, iLine2, iLine3;
        String sLine1, sLine2;
        CellTower tmpTower;
        Client tmpClient;
        NetworkOperator tmpOperator;
        
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();

            switch (command) {
                case "ADD_OPERATOR":
                    curLine = scanner.nextLine();
                    net.addOperator(curLine);
                    break;
                
                case "ADD_TOWER":
                    curLine = scanner.nextLine();
                    sLine1 = curLine;
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine2 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine3 = Integer.parseInt(curLine);

                    net.addTower(sLine1, iLine1, iLine2, iLine3);
                    break;
                
                case "REGISTER_OPERATOR_TOWER":
                    curLine = scanner.nextLine();
                    sLine1 = curLine;
                    curLine = scanner.nextLine();
                    sLine2 = curLine;
                    tmpOperator = net.getOperator(sLine1);
                    tmpTower = net.getTower(sLine2);

                    tmpTower.setOperator(tmpOperator);
                    break;
                
                case "ADD_CLIENT":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    tmpOperator = net.getOperator(curLine);
                    curLine = scanner.nextLine();
                    iLine2 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine3 = Integer.parseInt(curLine);

                    net.addClient(iLine1, tmpOperator, iLine2, iLine3);
                    break;
                
                case "TOWER_CLIENT_COUNT":
                    curLine = scanner.nextLine();
                    tmpTower = net.getTower(curLine);
                    if (tmpTower != null)
                    {
                        System.out.println(tmpTower.getClientSize());
                    } else
                    {
                        System.out.println("0");
                    }
                    break;
                
                case "MOVE_CLIENT":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine2 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine3 = Integer.parseInt(curLine);

                    tmpClient = net.getClient(iLine1);
                    tmpClient.moveClient(iLine2, iLine3);
                    break;

                case "CHANGE_OPERATOR":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    tmpClient = net.getClient(iLine1);
                    tmpOperator = net.getOperator(curLine);

                    tmpClient.setOperator(tmpOperator);
                    break;
                
                case "REMOVE_TOWER":
                    curLine = scanner.nextLine();
                    tmpTower = net.getTower(curLine);
                    net.remTower(tmpTower);
                    break;
                
                case "REMOVE_CLIENT":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    tmpClient = net.getClient(iLine1);

                    if (tmpClient != null) {
                        net.remClient(tmpClient);
                    }
                    break;
                
                case "OPERATOR_SUBSCRIBER_COUNT":
                    curLine = scanner.nextLine();
                    tmpOperator = net.getOperator(curLine);
                    if (tmpOperator != null) {
                        System.out.println(tmpOperator.getClientCount());
                    } else {
                        System.out.println("0");
                    }
                    break;
                
                // This is the only function that prints for itself
                // This is because we don't know how many prints we'll need
                case "NO_SIGNAL_COUNT":
                    net.printNoSignalOperators();
                    break;
                
                default:
                    break;
            }
        }

        scanner.close();
    }
}

class Network {
    private List<CellTower> netTowers = new ArrayList<>();
    private List<NetworkOperator> netOperators = new ArrayList<>();
    private List<Client> netClients = new ArrayList<>();

    // Add methods
    public void addTower(String id, int x, int y, int coverage)
    {
        CellTower tower = new CellTower(id, x, y, coverage);
        netTowers.add(tower);
    }

    public void addOperator(String name)
    {
        NetworkOperator op = new NetworkOperator(name);
        this.netOperators.add(op);
    }

    public void addClient(int number, NetworkOperator operator, int x, int y)
    {
        Client cl = new Client(number, operator, x, y);
        this.netClients.add(cl);
        operator.addClient(cl);
    }
    
    // Remove methods
    public void remTower(CellTower remTower)
    {
        this.netTowers.remove(remTower);
        remTower.remClients();
    }

    public void remOperator(NetworkOperator remOperator)
    {
        this.netOperators.remove(remOperator);
    }

    public void remClient(Client remClient)
    {
        if (remClient.getOperator() != null)
        {
            remClient.getOperator().remClient(remClient);
        }

        if (remClient.getTower() != null) {
            remClient.getTower().remClient(remClient);
        }
        this.netClients.remove(remClient);
    }

    // Get methods
    public CellTower getTower(String towSearchID)
    {
        for (CellTower tower : netTowers) {
            if (towSearchID.equals(tower.towID)) {
                return tower;
            }
        }
        return null;
    }

    public NetworkOperator getOperator(String opSearchName)
    {
        for (NetworkOperator operator : netOperators) {
            if (opSearchName.equals(operator.opName)) {
                return operator;
            }
        }
        return null;
    }

    public Client getClient(int clSearchNumber)
    {
        for (Client cl : netClients) {
            if (clSearchNumber == cl.phoneNumber) {
                return cl;
            }
        }
        return null;
    }

    public void printNoSignalOperators()
    {
        for (NetworkOperator curOperator : netOperators) {
            System.out.println(curOperator.opName + ": " + curOperator.noSignalClients()
                                + " phones without signal.");
        }
    }
}

class CellTower {
    public String towID;
    public int towX;
    public int towY;
    public int towCover;
    private NetworkOperator towOperator = null;
    private List<Client> towClients = new ArrayList<>();

    CellTower(String id,int x,int y,int coverage)
    {
        this.towID = id;
        this.towX = x;
        this.towY = y;
        this.towCover = coverage;
    }

    // Add methods
    public void setOperator(NetworkOperator newOperator)
    {
        this.towOperator = newOperator;
        newOperator.addTower(this);
    }

    public void addClient(Client newClient)
    {
        this.towClients.add(newClient);
    }
    
    // Remove methods
    public void remOperator(NetworkOperator remOperator)
    {
        this.towOperator = null;
    }

    public void remClient(Client remClient)
    {
        this.towClients.remove(remClient);
        remClient.moveClient();
    }

    public void remClients()
    {
        for (Client tower : towClients) {
            this.remClient(tower);
        }
    }

    // Get methods
    public NetworkOperator getOperator()
    {
        return towOperator;
    }

    public Client getClient(int clSearchNumber)
    {
        for (Client cl : towClients) {
            if (clSearchNumber == cl.phoneNumber) {
                return cl;
            }
        }
        return null;
    }

    public int getClientSize()
    {
        return this.towClients.size();
    }
}

class NetworkOperator {
    public String opName;
    private List<Client> opClients = new ArrayList<>();
    private List<CellTower> opTowers = new ArrayList<>();

    NetworkOperator(String name)
    {
        this.opName = name;
    }

    // Add methods
    public void addTower(CellTower newTower)
    {
        this.opTowers.add(newTower);
            
    }

    public void addClient(Client newClient)
    {
        this.opClients.add(newClient);
    }
    
    // Remove methods
    public void remTower(CellTower remTower)
    {
        this.opTowers.remove(remTower);
    }

    public void remClient(Client remClient)
    {
        this.opClients.remove(remClient);
    }

    // Get methods
    public CellTower getTower(String towSearchID)
    {
        for (CellTower tower : opTowers) {
            if (towSearchID.equals(tower.towID)) {
                return tower;
            }
        }
        return null;
    }

    public Client getClient(int clSearchNumber)
    {
        for (Client cl : opClients) {
            if (clSearchNumber == cl.phoneNumber) {
                return cl;
            }
        }
        return null;
    }
    
    // Main Functions
    public CellTower nearestTower(int curX, int curY)
    {
        double curClosest = Double.MAX_VALUE;
        double curDist;
        CellTower curTower = null;;

        for (CellTower tower : opTowers) {
            curDist = dist(curX, curY, tower.towX, tower.towY);
            if (curDist < curClosest && curDist <= tower.towCover) {
                curClosest = curDist;
                curTower = tower;
            }
        }
        return curTower;
    }

    public double dist(int x1, int y1, int x2, int y2)
    {
        double lSide = Math.pow((x2-x1), 2);
        double rSide = Math.pow((y2-y1), 2);
        return Math.sqrt(lSide + rSide);
    }

    public int getClientCount()
    {
        return this.opClients.size();
    }

    public int noSignalClients()
    {
        int count = 0;
        for (Client curClient : opClients) {
            if (curClient.getTower() == null) {
                count++;
            }
        }
        return count;
    }
}

class Client {
    public int phoneNumber;
    public int clX;
    public int clY;
    private NetworkOperator clOperator;
    private CellTower clTower = null;

    Client(int number, NetworkOperator operator, int x, int y)
    {
        this.phoneNumber = number;
        this.clOperator = operator;
        this.clX = x;
        this.clY = y;
        moveClient();
    }

    // Add methods
    public void setTower(CellTower newTower)
    {
        this.clTower = newTower;
    }

    public void setOperator(NetworkOperator newOperator)
    {
        this.clOperator = newOperator;
        this.moveClient();
    }
    
    // Remove methods
    public void remTower(CellTower remTower)
    {
        this.clTower = null;
    }

    public void remOperator(NetworkOperator remOperator)
    {
        this.clOperator = null;
        remTower(getTower());
    }

    // Get methods
    public CellTower getTower()
    {
        return this.clTower;
    }

    public NetworkOperator getOperator()
    {
        return this.clOperator;
    }

    // Used to update client's position and tower with new co-ordinates
    public void moveClient(int newX, int newY)
    {
        this.clX = newX;
        this.clY = newY;

        this.clTower = this.clOperator.nearestTower(this.clX, this.clY);
        if (this.clTower != null) {
            this.clTower.addClient(this);
            System.out.println("Client: " + this.phoneNumber + " moved to " + this.clTower.towID);
        }
        System.out.println("Client: " + this.phoneNumber + " did not move.");
    }

    // Updates client tower without updating co-ordinates
    public void moveClient()
    {
        this.clTower = this.clOperator.nearestTower(this.clX, this.clY);
        if (this.clTower != null) {
            this.clTower.addClient(this);
            System.out.println("Client: " + this.phoneNumber + " moved to " + this.clTower.towID);
        }
        System.out.println("Client: " + this.phoneNumber + " did not move.");
    }
}