package tennis;

import static tennis.TennisConstants.*;

public class Game {
    private int serverScore;
    private int receiverScore;

    public String call() {
        if (gameOver()) {
            return gameOverCall();
        }
        if (deuce()) {
            return DEUCE;
        }
        if (beyondDeuce()) {
            return createAdvantageCall();
        }
        return createSimpleCall();
    }

    private String createAdvantageCall() {
        return ADVANTAGE + SPACE + whoHasAdvantage();
    }

    private String whoHasAdvantage() {
        if (gameOver()) throw new InvalidGameOperationException("Game over! No one has Advantage.");
        if (deuce()) throw new InvalidGameOperationException("Deuce! No one has Advantage.");
        if (!beyondDeuce()) throw new InvalidGameOperationException("Not reached Deuce yet! No one has Advantage.");
        return (serverScore > receiverScore) ? SERVER : RECEIVER;
    }

    private String gameOverCall() {
        return GAME + SPACE + whoWon();
    }

    private String whoWon() {
        if (!gameOver()) throw new InvalidGameOperationException("Game not yet over! No one has won.");
        return (serverScore > receiverScore) ? SERVER : RECEIVER;
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
        return CALLS[serverScore] + SPACE + (serverScore == receiverScore ? ALL : CALLS[receiverScore]);
    }

    public void rallyForServer() {
        if (gameOver()) {
            throw new InvalidGameOperationException("Game over: server cannot win any more rallies!");
        }
        serverScore++;
    }

    public void rallyForReceiver() {
        if (gameOver()) {
            throw new InvalidGameOperationException("Game over: receiver cannot win any more rallies!");
        }
        receiverScore++;
    }
}
