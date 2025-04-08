//package W6;

import java.util.ArrayList;
import java.util.List;

class PlayerFactory {
    public static Player createPlayer(String type, String name, GameLobby lobby) {
        switch (type.toLowerCase()) {
            case "human":
                return new HumanPlayer(name, lobby);
            case "ai":
                return new AIPlayer(name, lobby);
            case "spectator":
                return new Spectator(name, lobby);
            case "admin":
                return new AdminPlayer(name, lobby);
        }
        return new HumanPlayer(name, lobby);
    }
}

class GameLobby {
    private List<Player> players = new ArrayList<>();

    void registerPlayer(Player player)
    {
        this.players.add(player);
        System.out.println("[GameLobby] " + player.getPlayerType() + " " + player.getName() 
                            + " has joined the lobby.");
    }

    void removePlayer(Player player)
    {
        this.players.remove(player);
        System.out.println("[GameLobby] " + player.getPlayerType() + " " + player.getName() 
                            + " has left the lobby.");
    }

    void sendMessage(String message, Player sender)
    {
        if (sender.getPlayerType() != "Spectator") {
            System.out.println("[" + sender.getName() + "] sends: " + "\"" + message + "\"");
            System.out.println("[GameLobby] Message from " + sender.getName() + ": " 
                                + "\"" + message + "\"");
            for (Player player : players) {
                if (sender != player) {
                    player.receiveMessage(message);
                }
            }
        } else {
            System.out.println("[GameLobby] Spectators cannot send messages.");
        }
    }

    void startMatch(){
        // Tmp later tracks if this is the first player printed
        int tmp = 0, count = this.players.size();
        for (Player player : players) {
            if (player.getPlayerType() == "Spectator" || player.getPlayerType() == "AdminPlayer") {
                count--;
            }
            if (count < 2) {
                break;
            }
        }

        if (count >= 2) {
            System.out.print("[GameLobby] Starting game with players: ");
            for (Player player : players) {
                if (tmp == 0 && player.getPlayerType() != "Spectator" && player.getPlayerType() != "AdminPlayer") {
                    System.out.print(player.getName());
                    tmp++;
                } else if (tmp != 0 && player.getPlayerType() != "Spectator" && player.getPlayerType() != "AdminPlayer") {
                    System.out.print(", " + player.getName());
                }
            }
            System.out.println("");
        } else {
            System.out.println("[GameLobby] Not enough players to start a match.");
        }
    }

    void kickPlayer(String name, AdminPlayer admin) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                if (player == admin) {
                    continue;
                }
                System.out.println("[GameLobby] Admin " + admin.getName() + " kicked "
                                    + player.getPlayerType() + " " + name + " from the lobby.");
                this.removePlayer(player);
                return;
            }
        }
        System.out.println("[GameLobby] Player " + name + " not found.");
    }
}

interface Player {
    void joinGame();

    void leaveGame();

    void sendMessage(String message);

    void receiveMessage(String message);

    String getPlayerType();

    String getName();
}

abstract class AbstractPlayer implements Player {
    protected String name;
    protected GameLobby lobby;

    public AbstractPlayer(String name, GameLobby lobby) {
        this.name = name;
        this.lobby = lobby;
    }

    @Override
    public void sendMessage(String message) {
        //System.out.println("[" + this.name + "] sends: " + "\"" + message + "\"");
        lobby.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("[" + this.name + "] received: " + "\"" + message + "\"");
    }

    public abstract String getPlayerType();

    public abstract String getName();
}

class HumanPlayer extends AbstractPlayer {
    public HumanPlayer(String name, GameLobby lobby) {
            super(name, lobby);
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has joined the lobby.");
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has left the lobby.");
    }

    @Override
    public String getPlayerType() {
        return "HumanPlayer";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class AIPlayer extends AbstractPlayer {
    public AIPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has joined the lobby.");
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has left the lobby.");
    }

    @Override
    public String getPlayerType() {
        return "AIPlayer";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class Spectator extends AbstractPlayer {
    public Spectator(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has joined the lobby.");
    }

    @Override
    public void leaveGame() {
        this.lobby.removePlayer(this);
        //System.out.println("[GameLobby] " + this.getPlayerType() + " " + this.name + " has left the lobby.");
    }

    @Override
    public String getPlayerType() {
        return "Spectator";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class AdminPlayer extends AbstractPlayer {
    public AdminPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public String getPlayerType() {
        return "AdminPlayer";
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
    }

    public void leaveGame() {
        this.lobby.removePlayer(this);
    }

    public void kickPlayer(String name) {
        lobby.kickPlayer(name, this);
    }
}