public class Game {
    private static final int MIN_RALLIES_TO_WIN_GAME = 4;
    private static final int MIN_DIFF_IN_RALLIES_TO_WIN_GAME = 2;
    private static final int MIN_RALLIES_TO_GET_TO_DEUCE = 3;
    private int serverScore;
    private int receiverScore;

    private static final String[] CALLS = {"Love", "Fifteen", "Thirty", "Forty"};

    public String call() {
        if (gameOver()) {
            return gameOverCall();
        }
        if (deuce()) {
            return "Deuce";
        }
        if (beyondDeuce()) {
            return createAdvantageCall();
        }
        return createSimpleCall();
    }

    private String createAdvantageCall() {
        String whoHasAdvantage = whoHasAdvantage();
        return "Advantage " + whoHasAdvantage;
    }

    private String whoHasAdvantage() {
        if (gameOver()) throw new RuntimeException("Game over!");
        if (deuce()) throw new RuntimeException("Deuce!");
        if (!beyondDeuce()) throw new RuntimeException("Not reached Deuce yet!");
        return (serverScore > receiverScore) ? "Server" : "Receiver";
    }

    private String gameOverCall() {
        String whoWon = whoWon();
        return "Game " + whoWon;
    }

    private String whoWon() {
        if (!gameOver()) throw new RuntimeException("Game not yet over!");
        return (serverScore > receiverScore) ? "Server" : "Receiver";
    }

    private boolean gameOver() {
        return (serverScore >= MIN_RALLIES_TO_WIN_GAME || receiverScore >= MIN_RALLIES_TO_WIN_GAME) &&
                (Math.abs(serverScore - receiverScore) >= MIN_DIFF_IN_RALLIES_TO_WIN_GAME);
    }

    private boolean deuce() {
        return (serverScore == receiverScore) && (serverScore >= MIN_RALLIES_TO_GET_TO_DEUCE);
    }

    private boolean beyondDeuce() {
        return (!gameOver()) && (!deuce()) && (serverScore > MIN_RALLIES_TO_GET_TO_DEUCE || receiverScore > MIN_RALLIES_TO_GET_TO_DEUCE);
    }

    private String createSimpleCall() {
        return CALLS[serverScore] + " " + (serverScore == receiverScore ? "All" : CALLS[receiverScore]);
    }

    public void rallyForServer() {
        serverScore++;
    }

    public void rallyForReceiver() {
        receiverScore++;
    }
}
