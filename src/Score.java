/**
 * Created with IntelliJ IDEA.
 * User: Thoughtworker
 * Date: 23/04/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Score {
    private String gameCall;

    public Score() {
        gameCall = "Love All";
    }

    public String call() {
        return gameCall;
    }

    public void rallyForServer() {
        gameCall = "Fifteen Love";
    }
}
