//package W3;

/*
 * Never coded it so that clients get added to the client list
 * Same with most other lists
 * :(
 * This is what is causing most of the errors
 */

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

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
        Operator tmpOperator;
        
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            //System.out.println(command);

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
                    tmpOperator = net.findOperator(sLine1);
                    tmpTower = net.findTower(sLine2);

                    tmpTower.assignOperator(tmpOperator);
                    break;
                
                case "ADD_CLIENT":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    tmpOperator = net.findOperator(curLine);
                    curLine = scanner.nextLine();
                    iLine2 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    iLine3 = Integer.parseInt(curLine);

                    net.addClient(iLine1, tmpOperator, iLine2, iLine3);
                    break;
                
                case "TOWER_CLIENT_COUNT":
                    curLine = scanner.nextLine();
                    tmpTower = net.findTower(curLine);
                    if (tmpTower != null)
                    {
                        System.out.println(tmpTower.clientCount());
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

                    tmpClient = net.findClient(iLine1);
                    tmpClient.moveClient(iLine2, iLine3);
                    break;

                case "CHANGE_OPERATOR":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    curLine = scanner.nextLine();
                    tmpClient = net.findClient(iLine1);
                    tmpOperator = net.findOperator(curLine);

                    tmpClient.changeOperator(tmpOperator);
                    break;
                
                case "REMOVE_TOWER":
                    curLine = scanner.nextLine();
                    tmpTower = net.findTower(curLine);
                    net.remTower(tmpTower);
                    break;
                
                case "REMOVE_CLIENT":
                    curLine = scanner.nextLine();
                    iLine1 = Integer.parseInt(curLine);
                    tmpClient = net.findClient(iLine1);
                    net.remClient(tmpClient);
                    break;
                
                case "OPERATOR_SUBSCRIBER_COUNT":
                    curLine = scanner.nextLine();
                    tmpOperator = net.findOperator(curLine);
                    System.out.println(tmpOperator.clientCount());
                    break;
                
                // This is the only function that prints for itself
                // This is because we don't know how many prints we'll need
                case "NO_SIGNAL_COUNT":
                    net.noSignalCount();
                    break;
                
                default:
                    break;
            }
        }

        scanner.close();
    }
}

class CellTower {
    public String id;
    public int x;
    public int y;
    public int coverage;
    public Operator owner;
    public List<Client> clients = new ArrayList<>();

    CellTower(String id, int x, int y, int coverage)
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.coverage = coverage;
    }

    public double findDist(int x1, int y1)
    {
        double lSide = Math.pow((this.x-x1), 2);
        double rSide = Math.pow((this.y-y1), 2);
        return Math.sqrt(lSide + rSide);
    }

    public int clientCount()
    {
        return this.clients.size();
    }

    public void assignOperator(Operator op)
    {
        this.owner = op;
        op.towers.add(this);
    }
}

class Operator {
    public String name;
    public List<Client> clients = new ArrayList<>();
    public List<CellTower> towers = new ArrayList<>();

    Operator(String name)
    {
        this.name = name;
    }

    public CellTower nearestTower(int x, int y)
    {
        int closest = Integer.MAX_VALUE;
        int curDist;
        CellTower curTower = null;
        for (CellTower tower : towers) {
            curDist = (int)tower.findDist(x, y);
            if (curDist < closest && curDist <= tower.coverage) {
                closest = curDist;
                curTower = tower;
            }
        }
        return curTower;
    }

    public int clientCount()
    {
        return this.clients.size();
    }

}

class Client {
    public int number;
    public int x;
    public int y;
    public Operator owner;
    public CellTower tower = null;

    Client(int number, Operator owner, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.owner = owner;
        moveClient(this.x, this.y);
    }

    public void moveClient(int newX, int newY)
    {
        this.x = newX;
        this.y = newY;

        if (this.tower != null) {
            this.tower.clients.remove(this);
        }
        this.tower = this.owner.nearestTower(newX, newY);
        if (this.tower != null) {
            this.tower.clients.add(this);
        }
    }

    public void changeOperator(Operator newOwner)
    {
        this.owner.clients.remove(this);
        newOwner.clients.add(this);
        this.owner = newOwner;
        moveClient(this.x, this.y);
    }
}

class Network {
    private List<CellTower> towers = new ArrayList<>();
    private List<Operator> operators = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public void addTower(String id, int x, int y, int coverage)
    {
        CellTower tower = new CellTower(id, x, y, coverage);
        towers.add(tower);
    }

    public void remTower(CellTower tower)
    {
        this.towers.remove(tower);
        for (Client client : tower.clients) {
            client.moveClient(client.x, client.y);
        }
    }

    public void addOperator(String name)
    {
        Operator op = new Operator(name);
        this.operators.add(op);
    }

    public void addClient(int number, Operator operator, int x, int y)
    {
        Client cl = new Client(number, operator, x, y);
        this.clients.add(cl);
        operator.clients.add(cl);
    }

    public void remClient(Client cl)
    {
        this.clients.remove(cl);
        cl.owner.clients.remove(cl);
    }

    public void noSignalCount()
    {
        int count;
        for (Operator op : this.operators) {
            count = 0;
            for (Client client : op.clients) {
                if (client.tower == null) {
                    count++;
                } else
                {
                    System.out.println("!Null = " + client.number);
                }
            }
            System.out.println(op.name + ": " + count + " phones without signal.");
        }
    }

    public CellTower findTower(String id)
    {
        for (CellTower tower : this.towers) {
            if (id.equals(tower.id)) {
                return tower;
            }
        }
        return null;
    }

    public Client findClient(int number)
    {
        for (Client client : this.clients) {
            if (client.number == number) {
                return client;
            }
        }
        return null;
    }

    public Operator findOperator(String name)
    {
        for (Operator op : this.operators) {
            if (name.equals(op.name)) {
                return op;
            }
        }
        return null;
    }
}