public class Score {
    private String gameCall;
    private int serverScore;
    private int receiverScore;

    private static final String[] CALLS = {"Love", "Fifteen", "Thirty", "Forty"};

    public String call() {
        return CALLS[serverScore] + " " + (serverScore == receiverScore? "All" : CALLS[receiverScore]);
    }

    public void rallyForServer() {
        serverScore ++;
    }

    public void rallyForReceiver() {
        receiverScore ++;
    }
}
