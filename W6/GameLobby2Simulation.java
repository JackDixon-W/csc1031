//package W6;

public class GameLobby2Simulation {
    public static void main(String[] args) {
        GameLobby lobby = new GameLobby();
        Player alice = new HumanPlayer("Alice", lobby);
        Player bot = new AIPlayer("BotX", lobby);
        Player bob = new Spectator("Bob", lobby);
        Player admin = new AdminPlayer("Charlie", lobby);

        alice.joinGame();
        bot.joinGame();
        bob.joinGame();
        admin.joinGame();

        admin.sendMessage("Welcome to the lobby!");
        ((AdminPlayer) admin).kickPlayer("Bob"); // Admin kicks Spectator Bob
        lobby.startMatch();
    }
}